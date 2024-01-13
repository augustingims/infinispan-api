package com.techops.infinispan.api.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PdmModel implements Serializable {

    private String childKey;
    private String parentKey;
    private String classId;
}
