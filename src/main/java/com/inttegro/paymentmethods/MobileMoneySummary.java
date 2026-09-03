package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class MobileMoneySummary {
    @JsonProperty("account_number")
    public String accountNumber;
    public MobileMoneyNetwork network;
}
