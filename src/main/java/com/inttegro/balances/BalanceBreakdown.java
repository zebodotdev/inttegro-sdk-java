package com.inttegro.balances;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceBreakdown {
    public BalanceAmount available;
    public BalanceAmount pending;
    public BalanceAmount reserved;
    public BalanceAmount refund;
    @JsonProperty("includes_transactions_before") public OffsetDateTime includesTransactionsBefore;
}
