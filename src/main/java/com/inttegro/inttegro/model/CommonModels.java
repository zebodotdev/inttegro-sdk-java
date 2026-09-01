package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonModels {
    public static class Money {
        public String currency;
        public Long value;

        public static Builder builder() { return new Builder(); }

        public static Money of(String currency, long value) {
            Money m = new Money();
            m.currency = currency;
            m.value = value;
            return m;
        }

        public static class Builder {
            private final Money money = new Money();
            public Builder currency(String currency) { money.currency = currency; return this; }
            public Builder value(Long value) { money.value = value; return this; }
            public Builder value(long value) { money.value = value; return this; }
            public Money build() { return money; }
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
