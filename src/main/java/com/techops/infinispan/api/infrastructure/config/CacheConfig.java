package com.techops.infinispan.api.infrastructure.config;

import com.techops.infinispan.api.domain.model.PdmModel;
import com.techops.infinispan.api.infrastructure.cache.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    
    private final RemoteCacheManager remoteCacheManager;

    private final CacheProperties cacheProperties;

    @Bean("myCache")
    public RemoteCache<String, PdmModel> myCache(){
        return remoteCacheManager.getCache(cacheProperties.getMyCache());
    }

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public InfinispanRemoteCacheCustomizer remoteCacheCustomizer() {
//        return b -> {
//            b.remoteCache("pdm-fca-party").marshaller(ProtoStreamMarshaller.class);
//            b.remoteCache("pdm-fcpfa-party").marshaller(ProtoStreamMarshaller.class);
//        };
//    }

//    @Bean("pdmFcaParty")
//    public RemoteCache<String, Object> pdmFcaParty(){
//        return remoteCacheManager.getCache(cacheProperties.getFcaCacheParty());
//    }
//
//    @Bean("pdmFcpfaParty")
//    public RemoteCache<String, Object> pdmFcpfaParty(){
//        return remoteCacheManager.getCache(cacheProperties.getFcpfaCacheParty());
//    }
}
