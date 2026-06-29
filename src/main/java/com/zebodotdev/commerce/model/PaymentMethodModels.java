package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.PaymentMethodType;

import java.util.Map;

public class PaymentMethodModels {
    public static class MobileMoneyParams {
        public String network;
        @JsonProperty("account_number")
        public String accountNumber;

        public static class Builder {
            private final MobileMoneyParams params = new MobileMoneyParams();
            public Builder network(String network) { params.network = network; return this; }
            public Builder accountNumber(String accountNumber) { params.accountNumber = accountNumber; return this; }
            public MobileMoneyParams build() { return params; }
        }
    }

    public static class PaymentMethodData {
        public PaymentMethodType type;
        @JsonProperty("mobile_money")
        public MobileMoneyParams mobileMoney;

        public static PaymentMethodData mobileMoney(java.util.function.Consumer<MobileMoneyParams.Builder> fn) {
            MobileMoneyParams.Builder b = new MobileMoneyParams.Builder();
            fn.accept(b);
            PaymentMethodData data = new PaymentMethodData();
            data.type = PaymentMethodType.MOBILE_MONEY;
            data.mobileMoney = b.build();
            return data;
        }
    }

    public static class PaymentMethodObject {
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

    public static class MobileMoneySummary {
        @JsonProperty("account_number")
        public String accountNumber;
        public String network;
    }

    public static class BankAccountSummary {
        @JsonProperty("ghana_bank_account")
        public GhanaBankAccountSummary ghanaBankAccount;
        public String type;
    }

    public static class GhanaBankAccountSummary {
        public String branch;
        public String name;
        @JsonProperty("account_number")
        public String accountNumber;
        @JsonProperty("sort_code")
        public String sortCode;
        @JsonProperty("swift_code")
        public String swiftCode;
    }

    public static class CardSummary {
        public String brand;
        @JsonProperty("expires_on")
        public String expiresOn;
        public CardParty issuer;
        public CardParty owner;
        public String type;
    }

    public static class CardParty {
        @JsonProperty("email_address")
        public String emailAddress;
        public String name;
        @JsonProperty("phone_number")
        public String phoneNumber;
        public String type;
    }

    public static class VerificationMetadata {
        @JsonProperty("completed_at")
        public String completedAt;
        @JsonProperty("initiated_at")
        public String initiatedAt;
        public String mechanism;
        @JsonProperty("request_id")
        public String requestId;
        public String type;
    }

    public static class PaymentMethodTypeSetting {
        public String type;
        public String name;
        public String description;
        public Boolean enabled;
        @JsonProperty("confirms_use")
        public Boolean confirmsUse;
    }

    public static class PaymentMethodSettings {
        @JsonProperty("mobile_money")
        public PaymentMethodTypeSetting mobileMoney;
        @JsonProperty("bank_account")
        public PaymentMethodTypeSetting bankAccount;
        public PaymentMethodTypeSetting card;
        public PaymentMethodTypeSetting motito;
    }

    public static class TokenizePaymentMethodParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("customer_id")
        public String customerId;
        @JsonProperty("payment_method_data")
        public PaymentMethodData paymentMethodData;
        @JsonProperty("verify_immediately")
        public Boolean verifyImmediately;
    }

    public static class VerifyPaymentMethodParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
    }

    public static class ConfirmPaymentMethodVerificationParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
        public String token;
    }

    public static class LookupPaymentMethodParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
    }

    public static class DeletePaymentMethodParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
    }

    public static class DeletePaymentMethodResponse {
        public boolean deleted;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
    }

    public static class VerificationDelivery {
        public String recipient;
        public String channel;
        @JsonProperty("sender_id")
        public String senderId;
    }

    public static class Verification {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
        public String status;
        @JsonProperty("token_sent_at")
        public String tokenSentAt;
        @JsonProperty("expires_at")
        public String expiresAt;
        public VerificationDelivery delivery;
    }

    public static class VerificationResponse {
        public Verification verification;
    }

    public static class PaymentMethodResponse {
        @JsonProperty("payment_method")
        public PaymentMethodObject paymentMethod;
    }

    public static class PaymentMethodSettingsResponse {
        public PaymentMethodSettings settings;
    }
}
