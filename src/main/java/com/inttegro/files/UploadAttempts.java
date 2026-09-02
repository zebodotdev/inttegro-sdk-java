package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadAttempts {
    @JsonProperty("max_attempts")
    public Integer maxAttempts;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadAttempts attempts = new UploadAttempts();
        public Builder maxAttempts(Integer maxAttempts) { attempts.maxAttempts = maxAttempts; return this; }
        public UploadAttempts build() { return attempts; }
    }
}
