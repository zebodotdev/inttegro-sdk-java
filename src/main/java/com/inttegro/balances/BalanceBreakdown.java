package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class BalanceBreakdown {
    public BalanceAmount available;
    public BalanceAmount pending;
    public BalanceAmount reserved;
    public BalanceAmount refund;
    @JsonProperty("includes_transactions_before") public String includesTransactionsBefore;
}
