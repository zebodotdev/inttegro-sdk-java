package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductPrice {
    public Integer amount;
    public String currency;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductPrice price = new ProductPrice();
        public Builder amount(Integer amount) { price.amount = amount; return this; }
        public Builder amount(int amount) { price.amount = amount; return this; }
        public Builder currency(String currency) { price.currency = currency; return this; }
        public ProductPrice build() { return price; }
    }
}
