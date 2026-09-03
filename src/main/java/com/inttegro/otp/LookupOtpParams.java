package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class LookupOtpParams {
    @JsonProperty("transaction_id") public String transactionId;

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private final LookupOtpParams params = new LookupOtpParams();

        public Builder transactionId(String transactionId) { params.transactionId = transactionId; return this; }
        public LookupOtpParams build() { return params; }
    }
}
