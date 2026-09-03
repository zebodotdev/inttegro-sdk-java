package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class LookupPaymentMethodParams {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupPaymentMethodParams params = new LookupPaymentMethodParams();
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public LookupPaymentMethodParams build() { return params; }
    }
}
