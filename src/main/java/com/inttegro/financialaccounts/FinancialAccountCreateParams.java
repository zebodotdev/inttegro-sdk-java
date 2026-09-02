package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class FinancialAccountCreateParams {
    public String label;
    public String type;
    public String reference;
    public String currency;
    public String description;
    @JsonProperty("pull_configuration") public PullPushConfig pullConfiguration;
    @JsonProperty("push_configuration") public PullPushConfig pushConfiguration;
    public WalletConfig wallet;
    @JsonProperty("bank_account") public BankAccountConfig bankAccount;
    @JsonProperty("dosh_account") public Map<String, Object> doshAccount;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public BankAccountOwner owner;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FinancialAccountCreateParams params = new FinancialAccountCreateParams();
        public Builder label(String label) { params.label = label; return this; }
        public Builder type(String type) { params.type = type; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder currency(String currency) { params.currency = currency; return this; }
        public Builder description(String description) { params.description = description; return this; }
        public Builder pullConfiguration(PullPushConfig pullConfiguration) { params.pullConfiguration = pullConfiguration; return this; }
        public Builder pushConfiguration(PullPushConfig pushConfiguration) { params.pushConfiguration = pushConfiguration; return this; }
        public Builder wallet(WalletConfig wallet) { params.wallet = wallet; return this; }
        public Builder bankAccount(BankAccountConfig bankAccount) { params.bankAccount = bankAccount; return this; }
        public Builder doshAccount(Map<String, Object> doshAccount) { params.doshAccount = doshAccount; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder owner(BankAccountOwner owner) { params.owner = owner; return this; }
        public FinancialAccountCreateParams build() { return params; }
    }
}
