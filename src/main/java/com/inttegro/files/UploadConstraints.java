package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadConstraints {
    @JsonProperty("content_types")
    public List<String> contentTypes;
    @JsonProperty("exact_size")
    public Integer exactSize;
    public List<String> extensions;
    public String filename;
    @JsonProperty("max_size")
    public Integer maxSize;
    @JsonProperty("min_size")
    public Integer minSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadConstraints constraints = new UploadConstraints();
        public Builder contentTypes(List<String> contentTypes) { constraints.contentTypes = contentTypes; return this; }
        public Builder exactSize(Integer exactSize) { constraints.exactSize = exactSize; return this; }
        public Builder extensions(List<String> extensions) { constraints.extensions = extensions; return this; }
        public Builder filename(String filename) { constraints.filename = filename; return this; }
        public Builder maxSize(Integer maxSize) { constraints.maxSize = maxSize; return this; }
        public Builder minSize(Integer minSize) { constraints.minSize = minSize; return this; }
        public UploadConstraints build() { return constraints; }
    }
}
