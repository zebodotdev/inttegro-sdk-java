package com.inttegro.paymentmethods;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class VerifyPaymentMethodParams {
    @JsonProperty("request_meta")
    public RequestMeta requestMeta;
    @JsonProperty("payment_method_id")
    public String paymentMethodId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final VerifyPaymentMethodParams params = new VerifyPaymentMethodParams();
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public VerifyPaymentMethodParams build() { return params; }
    }
}
