package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class UpdatePurchaseIntentParams {
    public String id;
    public PurchaseIntentQuantity quantity;
    @JsonProperty("expires_at")
    public String expiresAt;
    public Boolean reactivate;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UpdatePurchaseIntentParams params = new UpdatePurchaseIntentParams();
        public Builder id(String id) { params.id = id; return this; }
        public Builder quantity(PurchaseIntentQuantity quantity) { params.quantity = quantity; return this; }
        public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
        public Builder reactivate(Boolean reactivate) { params.reactivate = reactivate; return this; }
        public UpdatePurchaseIntentParams build() { return params; }
    }
}
