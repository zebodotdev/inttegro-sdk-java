package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadRequestFulfillParams {
    public String file;
    @JsonProperty("upload_url")
    public String uploadUrl;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadRequestFulfillParams params = new UploadRequestFulfillParams();
        public Builder file(Path file) { params.file = file.toString(); return this; }
        public Builder file(String file) { params.file = file; return this; }
        public Builder uploadUrl(String uploadUrl) { params.uploadUrl = uploadUrl; return this; }
        public UploadRequestFulfillParams build() { return params; }
    }
}
