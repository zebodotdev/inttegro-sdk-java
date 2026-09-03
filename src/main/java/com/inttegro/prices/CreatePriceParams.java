package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;

public class CreatePriceParams {
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public Money amount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreatePriceParams params = new CreatePriceParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder amount(Money amount) { params.amount = amount; return this; }
        public CreatePriceParams build() { return params; }
    }
}
