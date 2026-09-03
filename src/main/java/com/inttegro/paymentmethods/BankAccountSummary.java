package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.financialaccounts.BankAccountType;
import java.util.Map;

public class BankAccountSummary {
    @JsonProperty("ghana_bank_account")
    public GhanaBankAccountSummary ghanaBankAccount;
    public BankAccountType type;
}
