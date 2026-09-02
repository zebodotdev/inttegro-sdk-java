package com.inttegro.paymentmethods;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class TokenizePaymentMethodParams {
    @JsonProperty("request_meta")
    public RequestMeta requestMeta;
    @JsonProperty("customer_id")
    public String customerId;
    @JsonProperty("payment_method_data")
    public PaymentMethodData paymentMethodData;
    @JsonProperty("verify_immediately")
    public Boolean verifyImmediately;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final TokenizePaymentMethodParams params = new TokenizePaymentMethodParams();
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public Builder paymentMethodData(PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
        public Builder verifyImmediately(Boolean verifyImmediately) { params.verifyImmediately = verifyImmediately; return this; }
        public Builder verifyImmediately(boolean verifyImmediately) { params.verifyImmediately = verifyImmediately; return this; }
        public TokenizePaymentMethodParams build() { return params; }
    }
}
