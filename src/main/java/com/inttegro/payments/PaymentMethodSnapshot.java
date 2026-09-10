package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.paymentmethods.PaymentMethodType;

public class PaymentMethodSnapshot {
    public String id;
    @JsonProperty("bank_account") public PaymentMethodSnapshotBankAccount bankAccount;
    public PaymentMethodCard card;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("customer_id") public String customerId;
    @JsonProperty("mobile_money") public PaymentMethodSnapshotMobileMoney mobileMoney;
    public PaymentMethodOwner owner;
    public PaymentMethodType type;
    public boolean verified;
    @JsonProperty("verified_at") public OffsetDateTime verifiedAt;
}
