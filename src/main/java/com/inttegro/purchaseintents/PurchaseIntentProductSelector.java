package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PurchaseIntentProductSelector {
    public String id;
    @JsonProperty("variant_set_id")
    public String variantSetId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentProductSelector product = new PurchaseIntentProductSelector();
        public Builder id(String id) { product.id = id; return this; }
        public Builder variantSetId(String variantSetId) { product.variantSetId = variantSetId; return this; }
        public PurchaseIntentProductSelector build() { return product; }
    }
}
