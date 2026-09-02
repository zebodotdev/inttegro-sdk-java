package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class FinancialAccountToggleParams {
    @JsonProperty("account_id") public String accountId;
    @JsonProperty("unset_as_payout_destination") public Boolean unsetAsPayoutDestination;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FinancialAccountToggleParams params = new FinancialAccountToggleParams();
        public Builder accountId(String accountId) { params.accountId = accountId; return this; }
        public Builder unsetAsPayoutDestination(Boolean unsetAsPayoutDestination) { params.unsetAsPayoutDestination = unsetAsPayoutDestination; return this; }
        public Builder unsetAsPayoutDestination(boolean unsetAsPayoutDestination) { params.unsetAsPayoutDestination = unsetAsPayoutDestination; return this; }
        public FinancialAccountToggleParams build() { return params; }
    }
}
