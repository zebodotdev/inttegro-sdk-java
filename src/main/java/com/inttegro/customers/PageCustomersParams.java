package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PageCustomersParams {
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PageCustomersParams params = new PageCustomersParams();
        public Builder pageNumber(Integer pageNumber) {
            params.pageNumber = pageNumber;
            return this;
        }
        public Builder pageSize(Integer pageSize) {
            params.pageSize = pageSize;
            return this;
        }
        public PageCustomersParams build() { return params; }
    }
}
