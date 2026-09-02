package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class SetDefaultUnitPriceParams {
    @JsonProperty("product_id")
    public String productId;
    @JsonProperty("price_id")
    public String priceId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final SetDefaultUnitPriceParams params = new SetDefaultUnitPriceParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder priceId(String priceId) { params.priceId = priceId; return this; }
        public SetDefaultUnitPriceParams build() { return params; }
    }
}
