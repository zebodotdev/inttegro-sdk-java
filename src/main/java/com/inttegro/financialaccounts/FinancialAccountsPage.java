package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class FinancialAccountsPage {
    public Integer number;
    public Integer size;
    public FinancialAccount[] accounts;
}
