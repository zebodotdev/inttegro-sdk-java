package com.inttegro.filereferences;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FileReferenceInput {
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
