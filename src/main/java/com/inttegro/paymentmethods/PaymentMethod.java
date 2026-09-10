package com.inttegro.paymentmethods;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
public class PaymentMethod {
    public String id;
    public boolean active;
    @JsonProperty("archived_at")
    public OffsetDateTime archivedAt;
    @JsonProperty("customer_id")
    public String customerId;
    public PaymentMethodType type;
    @JsonProperty("mobile_money")
    public MobileMoneySummary mobileMoney;
    @JsonProperty("bank_account")
    public BankAccountSummary bankAccount;
    public CardSummary card;
    public PaymentMethodOwner owner;
    public PaymentMethodSupplied supplied;
    public VerificationMetadata verification;
    @JsonProperty("custom_data")
    public CustomData customData;
    @JsonProperty("expires_on")
    public OffsetDateTime expiresOn;
    public Boolean ephemeral;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("verified_at")
    public OffsetDateTime verifiedAt;
}
