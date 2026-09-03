package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class UpdatePaymentMethodParams {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    public Boolean active;
    public Boolean archived;
    public PaymentMethodOwner owner;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UpdatePaymentMethodParams params = new UpdatePaymentMethodParams();
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder active(Boolean active) { params.active = active; return this; }
        public Builder archived(Boolean archived) { params.archived = archived; return this; }
        public Builder owner(PaymentMethodOwner owner) { params.owner = owner; return this; }
        public UpdatePaymentMethodParams build() { return params; }
    }
}
