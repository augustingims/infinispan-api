package com.techops.infinispan.api.infrastructure.cache;

import com.techops.infinispan.api.domain.model.PdmModel;
import com.techops.infinispan.api.domain.proto.PdmProto;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheClientResolver {

    private final RemoteCacheManager remoteCacheManager;

    private final CacheProperties cacheProperties;

    public RemoteCache<String, PdmProto> get(String childSystem){
        return resolve(childSystem);
    }

    private RemoteCache<String, PdmProto> resolve(String childSystem){
        if("RC".equals(childSystem)){
            return remoteCacheManager.getCache(cacheProperties.getFcaCacheParty());
        } else if("M4".equals(childSystem)){
            return remoteCacheManager.getCache(cacheProperties.getFcpfaCacheParty());
        }  else {
            throw new IllegalStateException("Invalid cache");
        }
    }
}
