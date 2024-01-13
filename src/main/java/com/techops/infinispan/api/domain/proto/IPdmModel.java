package com.techops.infinispan.api.domain.proto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.infinispan.api.annotations.indexing.Indexed;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class IPdmModel {
    private String parentId;
    private String childId;
    private String objectVersion;
    private List<String> references;
    private String classId;

    @ProtoField(number = 1, required = true)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @ProtoField(number = 2, required = true)
    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
    @ProtoField(number = 3)
    public String getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(String objectVersion) {
        this.objectVersion = objectVersion;
    }
    @ProtoField(number = 4)
    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }
    @ProtoField(number = 5)
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
