package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodSnapshotBankAccount {
    public String type;
    @JsonProperty("ghana_bank_account") public PaymentMethodSnapshotGhanaBankAccount ghanaBankAccount;
}
