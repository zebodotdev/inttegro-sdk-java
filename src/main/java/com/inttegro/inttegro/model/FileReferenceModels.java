package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FileReferenceModels {
    public static class FileReferenceInput {
        @JsonProperty("file_id")
        public String fileId;
        public String field;
        public String reference;
        @JsonProperty("reference_kind")
        public String referenceKind;
        public String purpose;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileReferenceInput input = new FileReferenceInput();
            public Builder fileId(String fileId) { input.fileId = fileId; return this; }
            public Builder field(String field) { input.field = field; return this; }
            public Builder reference(String reference) { input.reference = reference; return this; }
            public Builder referenceKind(String referenceKind) { input.referenceKind = referenceKind; return this; }
            public Builder purpose(String purpose) { input.purpose = purpose; return this; }
            public FileReferenceInput build() { return input; }
        }
    }

    public static class FileReferenceReconcileParams {
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

    public static class FileReferenceReconcileResponse {
        public Boolean reconciled;
        public Object error;
    }
}
