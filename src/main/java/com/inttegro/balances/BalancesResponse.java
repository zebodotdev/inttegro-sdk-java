package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.Map;

public class BalancesResponse {
    public Map<String, BalanceBreakdown> balances;
}
