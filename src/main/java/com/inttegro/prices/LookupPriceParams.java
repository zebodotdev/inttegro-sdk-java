package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LookupPriceParams {
    @JsonProperty("price_id")
    public String priceId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupPriceParams params = new LookupPriceParams();
        public Builder priceId(String priceId) { params.priceId = priceId; return this; }
        public LookupPriceParams build() { return params; }
    }
}
