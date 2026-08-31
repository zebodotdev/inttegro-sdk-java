package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.PaymentMethodType;

import java.util.Map;

public class PaymentMethodModels {
    public static class MobileMoneyParams {
        public String network;
        @JsonProperty("account_number")
        public String accountNumber;

        public static Builder builder() { return new Builder(); }

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

        public static Builder builder() { return new Builder(); }

        public static PaymentMethodData mobileMoney(java.util.function.Consumer<MobileMoneyParams.Builder> fn) {
            MobileMoneyParams.Builder b = new MobileMoneyParams.Builder();
            fn.accept(b);
            PaymentMethodData data = new PaymentMethodData();
            data.type = PaymentMethodType.MOBILE_MONEY;
            data.mobileMoney = b.build();
            return data;
        }

        public static class Builder {
            private final PaymentMethodData data = new PaymentMethodData();
            public Builder type(PaymentMethodType type) { data.type = type; return this; }
            public Builder mobileMoney(MobileMoneyParams mobileMoney) { data.mobileMoney = mobileMoney; return this; }
            public PaymentMethodData build() { return data; }
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

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final TokenizePaymentMethodParams params = new TokenizePaymentMethodParams();
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public Builder customerId(String customerId) { params.customerId = customerId; return this; }
            public Builder paymentMethodData(PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
            public Builder verifyImmediately(Boolean verifyImmediately) { params.verifyImmediately = verifyImmediately; return this; }
            public Builder verifyImmediately(boolean verifyImmediately) { params.verifyImmediately = verifyImmediately; return this; }
            public TokenizePaymentMethodParams build() { return params; }
        }
    }

    public static class VerifyPaymentMethodParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final VerifyPaymentMethodParams params = new VerifyPaymentMethodParams();
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public VerifyPaymentMethodParams build() { return params; }
        }
    }

    public static class ConfirmPaymentMethodVerificationParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
        public String token;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ConfirmPaymentMethodVerificationParams params = new ConfirmPaymentMethodVerificationParams();
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public Builder token(String token) { params.token = token; return this; }
            public ConfirmPaymentMethodVerificationParams build() { return params; }
        }
    }

    public static class LookupPaymentMethodParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupPaymentMethodParams params = new LookupPaymentMethodParams();
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public LookupPaymentMethodParams build() { return params; }
        }
    }

    public static class PagePaymentMethodsParams {
        @JsonProperty("customer_id")
        public String customerId;
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PagePaymentMethodsParams params = new PagePaymentMethodsParams();
            public Builder customerId(String customerId) { params.customerId = customerId; return this; }
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public PagePaymentMethodsParams build() { return params; }
        }
    }

    public static class PaymentMethodActionParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PaymentMethodActionParams params = new PaymentMethodActionParams();
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public PaymentMethodActionParams build() { return params; }
        }
    }

    public static class PaymentMethodOwnerAddress {
        public String city;
        public String country;
        @JsonProperty("line1")
        public String line1;
        @JsonProperty("line2")
        public String line2;
        public String name;
        @JsonProperty("phone_number")
        public String phoneNumber;
        @JsonProperty("post_code")
        public String postCode;
        public String region;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PaymentMethodOwnerAddress address = new PaymentMethodOwnerAddress();
            public Builder city(String city) { address.city = city; return this; }
            public Builder country(String country) { address.country = country; return this; }
            public Builder line1(String line1) { address.line1 = line1; return this; }
            public Builder line2(String line2) { address.line2 = line2; return this; }
            public Builder name(String name) { address.name = name; return this; }
            public Builder phoneNumber(String phoneNumber) { address.phoneNumber = phoneNumber; return this; }
            public Builder postCode(String postCode) { address.postCode = postCode; return this; }
            public Builder region(String region) { address.region = region; return this; }
            public PaymentMethodOwnerAddress build() { return address; }
        }
    }

    public static class PaymentMethodOwner {
        public String name;
        public PaymentMethodOwnerAddress address;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PaymentMethodOwner owner = new PaymentMethodOwner();
            public Builder name(String name) { owner.name = name; return this; }
            public Builder address(PaymentMethodOwnerAddress address) { owner.address = address; return this; }
            public PaymentMethodOwner build() { return owner; }
        }
    }

    public static class UpdatePaymentMethodParams {
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        public Boolean active;
        public Boolean archived;
        public PaymentMethodOwner owner;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdatePaymentMethodParams params = new UpdatePaymentMethodParams();
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder active(Boolean active) { params.active = active; return this; }
            public Builder archived(Boolean archived) { params.archived = archived; return this; }
            public Builder owner(PaymentMethodOwner owner) { params.owner = owner; return this; }
            public UpdatePaymentMethodParams build() { return params; }
        }
    }

    public static class DeletePaymentMethodParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final DeletePaymentMethodParams params = new DeletePaymentMethodParams();
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public DeletePaymentMethodParams build() { return params; }
        }
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

    public static class PaymentMethodPage {
        public Integer number;
        public Integer size;
        @JsonProperty("payment_methods")
        public java.util.List<PaymentMethodObject> paymentMethods;
    }

    public static class PaymentMethodPageResponse {
        public PaymentMethodPage page;
        public Object error;
    }
}
