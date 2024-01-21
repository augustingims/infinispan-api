package com.techops.infinispan.api.infrastructure.config;

import com.techops.infinispan.api.infrastructure.cache.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {

    private final CacheProperties cacheProperties;
    
    @Bean
    @Lazy
    public RemoteCacheManager remoteCacheManager() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    configurationBuilder
        .clientIntelligence(ClientIntelligence.BASIC)
        .addServer()
        .host(cacheProperties.getHost())
        .port(cacheProperties.getPort())
        .security()
        .authentication()
        .username(cacheProperties.getUsername())
        .password(cacheProperties.getPassword())
        .maxRetries(cacheProperties.getMaxRetries())
        .marshaller(cacheProperties.getMarshaller());
        return new RemoteCacheManager(configurationBuilder.build(), true);
    }

//    @Bean("pdmFcaParty")
//    public RemoteCache<String, Object> pdmFcaParty(RemoteCacheManager remoteCacheManager){
//        return remoteCacheManager.getCache(cacheProperties.getFcaCacheParty());
//    }
//
//    @Bean("pdmFcpfaParty")
//    public RemoteCache<String, Object> pdmFcpfaParty(RemoteCacheManager remoteCacheManager){
//        return remoteCacheManager.getCache(cacheProperties.getFcpfaCacheParty());
//    }
}
