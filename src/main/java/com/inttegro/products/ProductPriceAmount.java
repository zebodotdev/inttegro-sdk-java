package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductPriceAmount {
    public String currency;
    public Long value;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductPriceAmount amount = new ProductPriceAmount();
        public Builder currency(String currency) { amount.currency = currency; return this; }
        public Builder value(Long value) { amount.value = value; return this; }
        public Builder value(long value) { amount.value = value; return this; }
        public ProductPriceAmount build() { return amount; }
    }
}
