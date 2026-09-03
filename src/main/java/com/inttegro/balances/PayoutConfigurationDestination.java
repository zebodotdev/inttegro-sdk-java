package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PayoutConfigurationDestination {
    @JsonProperty("financial_account_id") public String financialAccountId;
}
