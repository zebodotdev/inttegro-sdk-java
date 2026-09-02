package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.Map;

public enum BalanceTransactionType {
    @JsonProperty("payment")
    PAYMENT,
    @JsonProperty("refund")
    REFUND
}
