package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagePricesParams {
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;
    @JsonProperty("product_id")
    public String productId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PagePricesParams params = new PagePricesParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public Builder productId(String productId) { params.productId = productId; return this; }
        public PagePricesParams build() { return params; }
    }
}
