package com.techops.infinispan.api.application.service;

public interface ICacheService<T,R> {
    void addToCache(String childSystem, T data);

    R getFromCache(String childSystem,String childKey);

    void updateCache(String childSystem,String childKey, T data);

    void removeFromCache(String childSystem,String childKey);
}
