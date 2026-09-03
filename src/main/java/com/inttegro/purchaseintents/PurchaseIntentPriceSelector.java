package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.prices.PriceParams;

public class PurchaseIntentPriceSelector {
    public String id;
    public PriceParams nominal;
    public PurchaseIntentOriginalPriceParams original;
    @JsonProperty("original_id")
    public String originalId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentPriceSelector price = new PurchaseIntentPriceSelector();
        public Builder id(String id) { price.id = id; return this; }
        public Builder nominal(PriceParams nominal) { price.nominal = nominal; return this; }
        public Builder original(PurchaseIntentOriginalPriceParams original) { price.original = original; return this; }
        public Builder originalId(String originalId) { price.originalId = originalId; return this; }
        public PurchaseIntentPriceSelector build() { return price; }
    }
}
