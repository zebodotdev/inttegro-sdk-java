package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileContentsParams {
    public String disposition;
    @JsonProperty("file_id")
    public String fileId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileContentsParams params = new FileContentsParams();
        public Builder disposition(String disposition) { params.disposition = disposition; return this; }
        public Builder fileId(String fileId) { params.fileId = fileId; return this; }
        public FileContentsParams build() { return params; }
    }
}
