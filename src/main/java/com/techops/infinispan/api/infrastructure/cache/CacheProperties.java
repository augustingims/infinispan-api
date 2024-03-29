package com.techops.infinispan.api.infrastructure.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "infinispan")
@Data
public class CacheProperties {

    private String fcaCacheParty;
    private String fcpfaCacheParty;
    private String myCache;

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String marshaller;
    private Integer maxRetries;
}
