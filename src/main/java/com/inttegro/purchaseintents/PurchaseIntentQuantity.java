package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentQuantity {
    public Integer min;
    public Integer max;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentQuantity quantity = new PurchaseIntentQuantity();
        public Builder min(Integer min) { quantity.min = min; return this; }
        public Builder max(Integer max) { quantity.max = max; return this; }
        public PurchaseIntentQuantity build() { return quantity; }
    }
}
