package com.techops.infinispan.api.infrastructure.cache;

import com.techops.infinispan.api.domain.PdmModel;
import com.techops.infinispan.api.domain.PdmModelSchemaImpl;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.marshall.MarshallerUtil;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheClientResolver {

    private final RemoteCacheManager remoteCacheManager;

    private final CacheProperties cacheProperties;

    private final ConfigurableApplicationContext configurableApplicationContext;

    public RemoteCache<String, PdmModel> get(String childSystem) {
        initSerialisationModel();
        return resolve(childSystem);
    }

    private RemoteCache<String, PdmModel> resolve(String childSystem) {
        if("RC".equals(childSystem)){
            return remoteCacheManager.getCache(cacheProperties.getFcaCacheParty());
        } else if("M4".equals(childSystem)){
            return remoteCacheManager.getCache(cacheProperties.getFcpfaCacheParty());
        }  else {
            throw new IllegalStateException("Invalid cache");
        }
    }

    private void initSerialisationModel() {
        RemoteCacheManager remoteCacheManagerBean = this.configurableApplicationContext.getBean(RemoteCacheManager.class);
        SerializationContext ctx = MarshallerUtil.getSerializationContext(remoteCacheManagerBean);
        RemoteCache<String, String> protoMetadataCache = remoteCacheManagerBean.getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
        GeneratedSchema schema = new PdmModelSchemaImpl();
        protoMetadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
        schema.registerSchema(ctx);
        schema.registerMarshallers(ctx);
    }
}
