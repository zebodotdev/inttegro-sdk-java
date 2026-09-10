package com.inttegro.orders;

import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.prices.Price;
import com.inttegro.products.ProductType;

public class ProductLineItem {
    public String id;
    @JsonProperty("product_id") public String productId;
    @JsonProperty("price_id") public String priceId;
    public ProductType type;
    public String name;
    public String about;
    public String category;
    public Long quantity;
    public Price price;
    public String reference;
    @JsonProperty("tax_code")
    public String taxCode;
    @JsonProperty("custom_data")
    public CustomData customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductLineItem item = new ProductLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder productId(String productId) { item.productId = productId; return this; }
        public Builder priceId(String priceId) { item.priceId = priceId; return this; }
        public Builder type(ProductType type) { item.type = type; return this; }
        public Builder name(String name) { item.name = name; return this; }
        public Builder about(String about) { item.about = about; return this; }
        public Builder category(String category) { item.category = category; return this; }
        public Builder quantity(long qty) { item.quantity = qty; return this; }
        public Builder price(Price price) { item.price = price; return this; }
        public Builder reference(String ref) { item.reference = ref; return this; }
        public Builder taxCode(String tax) { item.taxCode = tax; return this; }
        public Builder customData(CustomData data) { item.customData = data; return this; }
        public ProductLineItem build() { return item; }
    }
}
