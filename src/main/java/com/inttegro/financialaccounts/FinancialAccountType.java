package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FinancialAccountType {
    @JsonProperty("wallet") WALLET,
    @JsonProperty("bank_account") BANK_ACCOUNT,
    @JsonProperty("dosh_account") DOSH_ACCOUNT
}
