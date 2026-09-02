package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class MobileMoneyParams {
    public String network;
    @JsonProperty("account_number")
    public String accountNumber;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MobileMoneyParams params = new MobileMoneyParams();
        public Builder network(String network) { params.network = network; return this; }
        public Builder accountNumber(String accountNumber) { params.accountNumber = accountNumber; return this; }
        public MobileMoneyParams build() { return params; }
    }
}
