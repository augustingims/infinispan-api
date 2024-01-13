package com.techops.infinispan.api.domain.proto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.api.annotations.indexing.Keyword;
import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

import java.io.Serializable;

@ProtoDoc("@Indexed(index=\"index01\")")
@AllArgsConstructor
@NoArgsConstructor
public class PdmProto implements Serializable {

    private String childKey;
    private String parentKey;
    private String classId;

    @ProtoField(number = 1, required = true)
    public String getChildKey() {
        return childKey;
    }

    public void setChildKey(String childKey) {
        this.childKey = childKey;
    }

    @ProtoField(number = 2, required = true)
    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    @ProtoField(number = 3)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
