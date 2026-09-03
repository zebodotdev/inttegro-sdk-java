package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.AmountParams;
import java.util.List;
import java.util.Map;

public class AddProductPriceParams {
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public AmountParams amount;
    @JsonProperty("set_as_default")
    public Boolean setAsDefault;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final AddProductPriceParams params = new AddProductPriceParams();
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder amount(AmountParams amount) { params.amount = amount; return this; }
        public Builder setAsDefault(Boolean setAsDefault) { params.setAsDefault = setAsDefault; return this; }
        public Builder setAsDefault(boolean setAsDefault) { params.setAsDefault = setAsDefault; return this; }
        public AddProductPriceParams build() { return params; }
    }
}
