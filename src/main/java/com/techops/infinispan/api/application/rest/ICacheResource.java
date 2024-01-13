package com.techops.infinispan.api.application.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Api Infinispan",
                version = "v0.0.1",
                description = "Api Infinispan documentation",
                license = @License(
                        name = "Apache License, Version 2.0",
                        url = "http://www.apache.org/licenses/"
                )
        )
)
public interface ICacheResource<T,R> {

    void addToCache(String childSystem, T data);

    R getFromCache(String childSystem,String childKey);

    void updateCache(String childSystem,String childKey, T data);

    void removeFromCache(String childSystem,String childKey);

}
