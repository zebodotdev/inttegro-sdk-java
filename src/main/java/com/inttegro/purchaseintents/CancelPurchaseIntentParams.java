package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class CancelPurchaseIntentParams {
    public String id;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CancelPurchaseIntentParams params = new CancelPurchaseIntentParams();
        public Builder id(String id) { params.id = id; return this; }
        public CancelPurchaseIntentParams build() { return params; }
    }
}
