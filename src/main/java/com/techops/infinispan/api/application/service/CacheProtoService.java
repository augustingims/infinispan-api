package com.techops.infinispan.api.application.service;

import com.techops.infinispan.api.domain.model.PdmModel;
import com.techops.infinispan.api.domain.proto.PdmProto;
import com.techops.infinispan.api.infrastructure.cache.CacheClientResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheProtoService implements ICacheService<PdmModel, PdmProto> {

    private final CacheClientResolver cacheClientResolver;

    @Override
    public void addToCache(String childSystem, PdmModel data) {
        log.info("Method addToCache");
        log.info("childSystem: {}", childSystem);
        PdmProto pdmProto = new PdmProto(data.getChildKey(), data.getParentKey(), data.getClassId());
        cacheClientResolver.get(childSystem).put(pdmProto.getChildKey(), pdmProto);
    }

    @Override
    public PdmProto getFromCache(String childSystem, String childKey) {
        log.info("Method getFromCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        return cacheClientResolver.get(childSystem).get(childKey);
    }

    @Override
    public void updateCache(String childSystem, String childKey, PdmModel data) {
        log.info("Method updateCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        PdmProto pdmProto = getFromCache(childSystem, childKey);
        pdmProto = new PdmProto(pdmProto.getChildKey(), data.getParentKey(), data.getClassId());
        cacheClientResolver.get(childSystem).replace(pdmProto.getChildKey(),pdmProto);
    }

    @Override
    public void removeFromCache(String childSystem, String childKey) {
        log.info("Method RemoveFromCache");
        log.info("childKey: {}", childKey);
        log.info("childSystem: {}", childSystem);
        cacheClientResolver.get(childSystem).remove(childKey);
    }
}
