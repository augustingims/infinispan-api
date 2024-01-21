package com.techops.infinispan.api.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ProtoDoc("@Indexed")
public class PdmModel implements Serializable {
    private String parentId;
    private String childId;
    private String objectVersion;
    private List<String> references;
    private String classId;

    @ProtoField(number = 1, required = true)
    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.NO)")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    @ProtoField(number = 2, required = true)
    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.NO)")
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
