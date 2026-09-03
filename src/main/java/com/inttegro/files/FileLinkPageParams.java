package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileLinkPageParams {
    @JsonProperty("file_id")
    public String fileId;
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;
    public FileLinkStatus status;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileLinkPageParams params = new FileLinkPageParams();
        public Builder fileId(String fileId) { params.fileId = fileId; return this; }
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public Builder status(FileLinkStatus status) { params.status = status; return this; }
        public FileLinkPageParams build() { return params; }
    }
}
