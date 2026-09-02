package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadRequestCancelParams {
    @JsonProperty("canceled_by")
    public Actor canceledBy;
    public String id;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadRequestCancelParams params = new UploadRequestCancelParams();
        public Builder canceledBy(Actor canceledBy) { params.canceledBy = canceledBy; return this; }
        public Builder id(String id) { params.id = id; return this; }
        public UploadRequestCancelParams build() { return params; }
    }
}
