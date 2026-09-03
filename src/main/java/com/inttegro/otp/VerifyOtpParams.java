package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.RequestMeta;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class VerifyOtpParams {
    @JsonProperty("request_meta") public RequestMeta requestMeta;
    public String recipient;
    public String token;
    @JsonProperty("transaction_id") public String transactionId;

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private final VerifyOtpParams params = new VerifyOtpParams();

        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder recipient(String recipient) { params.recipient = recipient; return this; }
        public Builder token(String token) { params.token = token; return this; }
        public Builder transactionId(String transactionId) { params.transactionId = transactionId; return this; }
        public VerifyOtpParams build() { return params; }
    }
}
