package com.inttegro.bankaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountConfig {
    public String id;
    public BankAccountType type;
    @JsonProperty("ghana_bank_account") public GhanaBankAccount ghanaBankAccount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final BankAccountConfig config = new BankAccountConfig();
        public Builder id(String id) { config.id = id; return this; }
        public Builder type(BankAccountType type) { config.type = type; return this; }
        public Builder ghanaBankAccount(GhanaBankAccount ghanaBankAccount) { config.ghanaBankAccount = ghanaBankAccount; return this; }
        public BankAccountConfig build() { return config; }
    }
}
