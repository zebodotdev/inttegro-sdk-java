package com.inttegro.filereferences;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FileReferenceReconcileParams {
    @JsonProperty("resource_type")
    public String resourceType;
    @JsonProperty("resource_id")
    public String resourceId;
    public List<FileReferenceInput> references;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileReferenceReconcileParams params = new FileReferenceReconcileParams();
        public Builder resourceType(String resourceType) { params.resourceType = resourceType; return this; }
        public Builder resourceId(String resourceId) { params.resourceId = resourceId; return this; }
        public Builder references(List<FileReferenceInput> references) { params.references = references; return this; }
        public FileReferenceReconcileParams build() { return params; }
    }
}
