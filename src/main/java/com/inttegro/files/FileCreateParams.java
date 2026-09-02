package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileCreateParams {
    public String file;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    public String purpose;
    public String title;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileCreateParams params = new FileCreateParams();
        public Builder file(Path file) { params.file = file.toString(); return this; }
        public Builder file(String file) { params.file = file; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder title(String title) { params.title = title; return this; }
        public FileCreateParams build() { return params; }
    }
}
