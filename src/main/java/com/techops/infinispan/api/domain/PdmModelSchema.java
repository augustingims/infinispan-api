package com.techops.infinispan.api.domain;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
        includeClasses = {
                PdmModel.class
        },
        schemaFileName = "pdmModel.proto",
        schemaFilePath = "proto/",
        schemaPackageName = "com.techops.infinispan.api.domain")
public interface PdmModelSchema extends GeneratedSchema {}
