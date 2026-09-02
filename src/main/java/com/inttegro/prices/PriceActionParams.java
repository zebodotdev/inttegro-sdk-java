package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceActionParams {
    @JsonProperty("price_id")
    public String priceId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PriceActionParams params = new PriceActionParams();
        public Builder priceId(String priceId) { params.priceId = priceId; return this; }
        public PriceActionParams build() { return params; }
    }
}
