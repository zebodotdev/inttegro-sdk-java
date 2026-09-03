package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PaymentMethodActionParams {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PaymentMethodActionParams params = new PaymentMethodActionParams();
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public PaymentMethodActionParams build() { return params; }
    }
}
