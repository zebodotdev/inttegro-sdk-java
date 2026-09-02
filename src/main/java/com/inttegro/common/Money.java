package com.inttegro.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Money {
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
