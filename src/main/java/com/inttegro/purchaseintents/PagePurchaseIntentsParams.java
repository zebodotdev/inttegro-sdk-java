package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PagePurchaseIntentsParams {
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PagePurchaseIntentsParams params = new PagePurchaseIntentsParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public PagePurchaseIntentsParams build() { return params; }
    }
}
