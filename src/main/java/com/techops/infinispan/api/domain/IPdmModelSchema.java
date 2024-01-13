package com.techops.infinispan.api.domain;

import com.techops.infinispan.api.domain.proto.IPdmModel;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
        includeClasses = {
                IPdmModel.class
        },
        schemaFileName = "pdmModel.proto",
        schemaFilePath = "proto/",
        schemaPackageName = "com.techops.infinispan.api.domain.proto")
public interface IPdmModelSchema extends GeneratedSchema {}
