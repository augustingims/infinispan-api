package com.techops.infinispan.api.application.service;

import com.techops.infinispan.api.domain.model.PdmModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.client.hotrod.RemoteCache;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService implements ICacheService<PdmModel, PdmModel> {

    private final RemoteCache<String, PdmModel> myCache;

    @Override
    public void addToCache(String childSystem, PdmModel data) {
        log.info("Method addToCache");
        myCache.put(data.getChildKey(), data);
    }

    @Override
    public PdmModel getFromCache(String childSystem, String childKey) {
        log.info("Method getFromCache");
        log.info("childKey: {}", childKey);
        return myCache.get(childKey);
    }

    @Override
    public void updateCache(String childSystem, String childKey, PdmModel data) {
        log.info("Method updateCache");
        log.info("childKey: {}", childKey);
        PdmModel pdmModel = getFromCache(childSystem, childKey);
        pdmModel.setParentKey(data.getParentKey());
        pdmModel.setClassId(data.getClassId());
        myCache.replace(childKey, pdmModel);
    }

    @Override
    public void removeFromCache(String childSystem, String childKey) {
        log.info("Method RemoveFromCache");
        log.info("childKey: {}", childKey);
        myCache.remove(childKey);
    }
}
