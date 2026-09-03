package com.inttegro.money;

/** An amount supplied in an API request, expressed in the currency's smallest unit. */
public class AmountParams {
    public Currency currency;
    public Long value;

    public static AmountParams of(Currency currency, long value) {
        return builder().currency(currency).value(value).build();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        protected final AmountParams amount = new AmountParams();
        public Builder currency(Currency currency) { amount.currency = currency; return this; }
        public Builder value(long value) { amount.value = value; return this; }
        public AmountParams build() { return amount; }
    }
}
