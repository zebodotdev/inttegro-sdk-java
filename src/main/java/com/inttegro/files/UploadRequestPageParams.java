package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadRequestPageParams {
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;
    public String purpose;
    public ResourceRef resource;
    public String status;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadRequestPageParams params = new UploadRequestPageParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder resource(ResourceRef resource) { params.resource = resource; return this; }
        public Builder status(String status) { params.status = status; return this; }
        public UploadRequestPageParams build() { return params; }
    }
}
