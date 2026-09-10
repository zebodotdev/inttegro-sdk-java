package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
public class MobileMoneySummary {
    @JsonProperty("account_number")
    public String accountNumber;
    public String last4;
    public MobileMoneyNetwork network;
}
