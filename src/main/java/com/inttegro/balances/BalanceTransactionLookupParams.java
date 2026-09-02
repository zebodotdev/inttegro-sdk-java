package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.Map;

public class BalanceTransactionLookupParams {
    @JsonProperty("transaction_id") public String transactionId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final BalanceTransactionLookupParams params = new BalanceTransactionLookupParams();
        public Builder transactionId(String transactionId) { params.transactionId = transactionId; return this; }
        public BalanceTransactionLookupParams build() { return params; }
    }
}
