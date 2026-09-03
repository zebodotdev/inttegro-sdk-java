package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.AmountParams;

/** Parameters for creating a stored catalog price. */
public class CatalogPriceParams {
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public AmountParams amount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CatalogPriceParams params = new CatalogPriceParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder amount(AmountParams amount) { params.amount = amount; return this; }
        public CatalogPriceParams build() { return params; }
    }
}
