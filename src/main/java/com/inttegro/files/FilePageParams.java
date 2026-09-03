package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FilePageParams {
    @JsonProperty("created_after")
    public String createdAfter;
    @JsonProperty("created_before")
    public String createdBefore;
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;
    public String purpose;
    public FileStatus status;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FilePageParams params = new FilePageParams();
        public Builder createdAfter(String createdAfter) { params.createdAfter = createdAfter; return this; }
        public Builder createdBefore(String createdBefore) { params.createdBefore = createdBefore; return this; }
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder status(FileStatus status) { params.status = status; return this; }
        public FilePageParams build() { return params; }
    }
}
