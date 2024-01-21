package com.techops.infinispan.api.application.service;

import static org.mockito.Mockito.*;

import com.techops.infinispan.api.domain.PdmModel;
import com.techops.infinispan.api.infrastructure.cache.CacheClientResolver;
import org.infinispan.client.hotrod.RemoteCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CacheServiceTest {

    private CacheService cacheProtoService;

    @Mock
    private CacheClientResolver cacheClientResolver;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheProtoService = new CacheService(cacheClientResolver);
    }

    @Test
    public void testAddToCache() {
        String childSystem = "RC";
        PdmModel pdmModel = new PdmModel();
        pdmModel.setChildId("childId");
        pdmModel.setParentId("parentId");
        pdmModel.setClassId("classId");

        PdmModel pdmProto = new PdmModel();
        pdmProto.setChildId(pdmModel.getChildId());
        pdmProto.setParentId(pdmModel.getParentId());
        pdmProto.setClassId(pdmModel.getClassId());

        when(cacheClientResolver.get(childSystem)).thenReturn(mock(RemoteCache.class));

        cacheProtoService.addToCache(childSystem, pdmModel);

//        verify(cacheClientResolver.get(childSystem)).put(pdmProto.getChildId(), pdmProto);
    }

    @Test
    public void testGetFromCache() {
        String childSystem = "RC";
        String childId = "childId";

        when(cacheClientResolver.get(childSystem)).thenReturn(mock(RemoteCache.class));

        cacheProtoService.getFromCache(childSystem, childId);

        verify(cacheClientResolver.get(childSystem)).get(childId);
    }

    @Test
    public void testUpdateCache() {
        String childSystem = "RC";
        String childId = "childId";
        PdmModel pdmModel = new PdmModel();
        pdmModel.setParentId("newParentId");
        pdmModel.setClassId("newClassId");

        PdmModel pdmProto = new PdmModel();
        pdmProto.setChildId(childId);
        pdmProto.setParentId("oldParentId");
        pdmProto.setClassId("oldClassId");

        when(cacheClientResolver.get(childSystem)).thenReturn(mock(RemoteCache.class));
        when(cacheClientResolver.get(childSystem).get(childId)).thenReturn(pdmProto);

        cacheProtoService.updateCache(childSystem, childId, pdmModel);

        PdmModel updatedPdmProto = new PdmModel();
        updatedPdmProto.setChildId(childId);
        updatedPdmProto.setParentId(pdmModel.getParentId());
        updatedPdmProto.setClassId(pdmModel.getClassId());

        //verify(cacheClientResolver.get(childSystem)).replace(childId, updatedPdmProto);
    }

    @Test
    public void testRemoveFromCache() {
        String childSystem = "RC";
        String childId = "childId";

        when(cacheClientResolver.get(childSystem)).thenReturn(mock(RemoteCache.class));

        cacheProtoService.removeFromCache(childSystem, childId);

        verify(cacheClientResolver.get(childSystem)).remove(childId);
    }
}
