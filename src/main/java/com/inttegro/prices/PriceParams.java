package com.inttegro.prices;

import com.inttegro.money.Currency;

/** An inline price supplied in a request. Its amount fields are embedded on the wire. */
public class PriceParams {
    public Currency currency;
    public Long value;

    public static PriceParams of(Currency currency, long value) {
        return builder().currency(currency).value(value).build();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PriceParams price = new PriceParams();
        public Builder currency(Currency currency) { price.currency = currency; return this; }
        public Builder value(long value) { price.value = value; return this; }
        public PriceParams build() { return price; }
    }
}
