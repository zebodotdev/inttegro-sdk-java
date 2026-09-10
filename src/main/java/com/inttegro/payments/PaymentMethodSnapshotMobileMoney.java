package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.paymentmethods.MobileMoneyNetwork;

public class PaymentMethodSnapshotMobileMoney {
    public MobileMoneyNetwork network;
    @JsonProperty("account_number") public String accountNumber;
    public String last4;
}
