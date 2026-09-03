package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.MobileMoneyNetwork;
import java.util.Map;

public class MobileMoneyParams {
    public MobileMoneyNetwork network;
    @JsonProperty("account_number")
    public String accountNumber;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MobileMoneyParams params = new MobileMoneyParams();
        public Builder network(MobileMoneyNetwork network) { params.network = network; return this; }
        public Builder accountNumber(String accountNumber) { params.accountNumber = accountNumber; return this; }
        public MobileMoneyParams build() { return params; }
    }
}
