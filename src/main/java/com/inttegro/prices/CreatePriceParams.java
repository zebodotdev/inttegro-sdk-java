package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePriceParams {
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public String currency;
    public Long amount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreatePriceParams params = new CreatePriceParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder currency(String currency) { params.currency = currency; return this; }
        public Builder amount(Long amount) { params.amount = amount; return this; }
        public Builder amount(long amount) { params.amount = amount; return this; }
        public CreatePriceParams build() { return params; }
    }
}
