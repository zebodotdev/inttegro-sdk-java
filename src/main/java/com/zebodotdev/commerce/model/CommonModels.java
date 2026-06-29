package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonModels {
    public static class Money {
        public String currency;
        public Long value;

        public static Money of(String currency, long value) {
            Money m = new Money();
            m.currency = currency;
            m.value = value;
            return m;
        }
    }

    public enum PaymentMethodType {
        @JsonProperty("mobile_money")
        MOBILE_MONEY,
        @JsonProperty("bank_account")
        BANK_ACCOUNT,
        @JsonProperty("card")
        CARD,
        @JsonProperty("motito")
        MOTITO
    }

    public enum LineItemType {
        @JsonProperty("product")
        PRODUCT,
        @JsonProperty("fee")
        FEE,
        @JsonProperty("shipping")
        SHIPPING
    }

    public enum ChimeRecipientType {
        @JsonProperty("phone")
        PHONE,
        @JsonProperty("email")
        EMAIL
    }

    public enum ChimeTransport {
        @JsonProperty("sms")
        SMS,
        @JsonProperty("email")
        EMAIL
    }
}
