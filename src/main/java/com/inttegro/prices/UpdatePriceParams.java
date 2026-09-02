package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePriceParams {
    @JsonProperty("price_id")
    public String priceId;
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UpdatePriceParams params = new UpdatePriceParams();
        public Builder priceId(String priceId) { params.priceId = priceId; return this; }
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public UpdatePriceParams build() { return params; }
    }
}
