package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.prices.PriceParams;
import com.inttegro.products.ProductType;
import java.util.Map;

/** Product line-item fields supplied in an order request. */
public class ProductLineItemParams {
    public String id;
    public ProductType type;
    public String name;
    public String about;
    public Long quantity;
    public PriceParams price;
    @JsonProperty("price_id") public String priceId;
    public String reference;
    @JsonProperty("tax_code") public String taxCode;
    @JsonProperty("custom_data") public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final ProductLineItemParams item = new ProductLineItemParams();
        public Builder id(String id) { item.id = id; return this; }
        public Builder type(ProductType type) { item.type = type; return this; }
        public Builder name(String name) { item.name = name; return this; }
        public Builder about(String about) { item.about = about; return this; }
        public Builder quantity(long quantity) { item.quantity = quantity; return this; }
        public Builder price(PriceParams price) { item.price = price; return this; }
        public Builder priceId(String priceId) { item.priceId = priceId; return this; }
        public Builder reference(String reference) { item.reference = reference; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
        public ProductLineItemParams build() { return item; }
    }
}
