package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class ConfirmPaymentMethodVerificationParams {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
    public String token;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ConfirmPaymentMethodVerificationParams params = new ConfirmPaymentMethodVerificationParams();
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public Builder token(String token) { params.token = token; return this; }
        public ConfirmPaymentMethodVerificationParams build() { return params; }
    }
}
