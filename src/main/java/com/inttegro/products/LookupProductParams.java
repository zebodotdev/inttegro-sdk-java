package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class LookupProductParams {
    @JsonProperty("product_id")
    public String productId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupProductParams params = new LookupProductParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public LookupProductParams build() { return params; }
    }
}
