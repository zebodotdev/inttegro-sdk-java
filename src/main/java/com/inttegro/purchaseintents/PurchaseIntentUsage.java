package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentUsage {
    @JsonProperty("single_use")
    public Boolean singleUse;
    @JsonProperty("multi_use")
    public Boolean multiUse;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentUsage usage = new PurchaseIntentUsage();
        public Builder singleUse(Boolean singleUse) { usage.singleUse = singleUse; return this; }
        public Builder multiUse(Boolean multiUse) { usage.multiUse = multiUse; return this; }
        public PurchaseIntentUsage build() { return usage; }
    }
}
