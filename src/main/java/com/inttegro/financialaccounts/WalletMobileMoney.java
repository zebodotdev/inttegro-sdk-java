package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class WalletMobileMoney {
    public String id;
    @JsonProperty("account_number") public String accountNumber;
    public String network;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final WalletMobileMoney mobileMoney = new WalletMobileMoney();
        public Builder id(String id) { mobileMoney.id = id; return this; }
        public Builder accountNumber(String accountNumber) { mobileMoney.accountNumber = accountNumber; return this; }
        public Builder network(String network) { mobileMoney.network = network; return this; }
        public WalletMobileMoney build() { return mobileMoney; }
    }
}
