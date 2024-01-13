package com.techops.infinispan.api;

import com.techops.infinispan.api.domain.IPdmModelSchemaImpl;
import com.techops.infinispan.api.domain.proto.PdmProto;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.ServerStatistics;
import org.infinispan.client.hotrod.marshall.MarshallerUtil;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class InfinispanApiApplication implements CommandLineRunner {

	private final RemoteCacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(InfinispanApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SerializationContext ctx = MarshallerUtil.getSerializationContext(cacheManager);
		RemoteCache<String, String> protoMetadataCache = cacheManager.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
		String msgSchemaFile = null;
		try {
			ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
			msgSchemaFile = protoSchemaBuilder.fileName("pdmProto.proto").packageName("pdmProto").addClass(PdmProto.class).build(ctx);
			protoMetadataCache.put("pdmProto.proto", msgSchemaFile);

			GeneratedSchema schema = new IPdmModelSchemaImpl();
			protoMetadataCache.put(schema.getProtoFileName(), schema.getProtoFile());

		} catch (Exception e) {
			throw new RuntimeException("Failed to build protobuf definition from 'PdmProto class'", e);
		}

		String errors = protoMetadataCache.get(ProtobufMetadataManagerConstants.ERRORS_KEY_SUFFIX);
		if (errors != null) {
			throw new IllegalStateException("Some Protobuf schema files contain errors: " + errors + "\nSchema :\n" + msgSchemaFile);
		}
	}
}
