package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.bankaccounts.BankAccountOwner;
import java.util.Map;

public class FinancialAccountUpdateParams {
    @JsonProperty("account_id") public String accountId;
    public String label;
    public String description;
    public String reference;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public BankAccountOwner owner;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FinancialAccountUpdateParams params = new FinancialAccountUpdateParams();
        public Builder accountId(String accountId) { params.accountId = accountId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder description(String description) { params.description = description; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder owner(BankAccountOwner owner) { params.owner = owner; return this; }
        public FinancialAccountUpdateParams build() { return params; }
    }
}
