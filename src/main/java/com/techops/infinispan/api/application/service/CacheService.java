package com.techops.infinispan.api.application.service;

import com.techops.infinispan.api.domain.PdmModel;
import com.techops.infinispan.api.infrastructure.cache.CacheClientResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService implements ICacheService<PdmModel, PdmModel> {

    private final CacheClientResolver cacheClientResolver;

    @Override
    public void addToCache(String childSystem, PdmModel pdmModel) {
        log.info("Method addToCache");
        log.info("childSystem: {}", childSystem);
        cacheClientResolver.get(childSystem).put(pdmModel.getChildId(), pdmModel);
    }

    @Override
    public PdmModel getFromCache(String childSystem, String childKey) {
        log.info("Method getFromCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        return cacheClientResolver.get(childSystem).get(childKey);
    }

    @Override
    public void updateCache(String childSystem, String childKey, PdmModel pdmModel) {
        log.info("Method updateCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        PdmModel cacheExist = getFromCache(childSystem, childKey);
        if(cacheExist != null){
            cacheExist.setReferences(cacheExist.getReferences());
            cacheClientResolver.get(childSystem).replace(cacheExist.getChildId(),cacheExist);
        }
    }

    @Override
    public void removeFromCache(String childSystem, String childKey) {
        log.info("Method RemoveFromCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        cacheClientResolver.get(childSystem).remove(childKey);
    }
}
