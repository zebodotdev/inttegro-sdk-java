package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseIntentUsage {
    @JsonProperty("single_use")
    public Boolean singleUse;
    @JsonProperty("multi_use")
    public Boolean multiUse;
    public PurchaseIntentUsageOrder order;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentUsage usage = new PurchaseIntentUsage();
        public Builder singleUse(Boolean singleUse) { usage.singleUse = singleUse; return this; }
        public Builder multiUse(Boolean multiUse) { usage.multiUse = multiUse; return this; }
        public Builder order(PurchaseIntentUsageOrder order) { usage.order = order; return this; }
        public PurchaseIntentUsage build() { return usage; }
    }
}
