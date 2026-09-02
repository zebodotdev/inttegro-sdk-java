package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class FinancialAccountLookupParams {
    @JsonProperty("account_id") public String accountId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FinancialAccountLookupParams params = new FinancialAccountLookupParams();
        public Builder accountId(String accountId) { params.accountId = accountId; return this; }
        public FinancialAccountLookupParams build() { return params; }
    }
}
