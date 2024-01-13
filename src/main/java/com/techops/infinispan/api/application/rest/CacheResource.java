package com.techops.infinispan.api.application.rest;

import com.techops.infinispan.api.application.service.ICacheService;
import com.techops.infinispan.api.domain.model.PdmModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheResource implements ICacheResource<PdmModel, PdmModel>{

    private final ICacheService<PdmModel, PdmModel> iCacheService;

    @PostMapping("/add")
    public void addToCache(@RequestParam String childSystem,@RequestBody PdmModel value) {
        iCacheService.addToCache(childSystem,value);
    }

    @GetMapping("/get/{childKey}")
    public PdmModel getFromCache(@PathVariable String childKey, @RequestParam String childSystem) {
        return iCacheService.getFromCache(childSystem,childKey);
    }

    @PutMapping("/update/{childKey}")
    public void updateCache(@PathVariable String childKey, @RequestParam String childSystem, @RequestBody PdmModel value) {
        iCacheService.updateCache(childSystem,childKey, value);
    }

    @DeleteMapping("/remove/{childKey}")
    public void removeFromCache(@PathVariable String childKey, @RequestParam String childSystem) {
        iCacheService.removeFromCache(childSystem,childKey);
    }
}
