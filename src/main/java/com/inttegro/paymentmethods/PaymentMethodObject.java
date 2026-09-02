package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodObject {
    public String id;
    @JsonProperty("customer_id")
    public String customerId;
    public PaymentMethodType type;
    @JsonProperty("mobile_money")
    public MobileMoneySummary mobileMoney;
    @JsonProperty("bank_account")
    public BankAccountSummary bankAccount;
    public CardSummary card;
    public VerificationMetadata verification;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    @JsonProperty("expires_on")
    public String expiresOn;
    @JsonProperty("created_at")
    public String createdAt;
    public boolean verified;
    @JsonProperty("verified_at")
    public String verifiedAt;
}
