package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.prices.Price;
import com.inttegro.customers.Address;
import com.inttegro.customers.BillingDetails;
import com.inttegro.customers.CustomerData;
import com.inttegro.customers.Shipping;
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.products.ProductType;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ProductLineItem {
    public String id;
    public ProductType type;
    public String name;
    public String about;
    public Long quantity;
    public Price price;
    public String reference;
    @JsonProperty("tax_code")
    public String taxCode;
    @JsonProperty("custom_data")
    public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductLineItem item = new ProductLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder type(ProductType type) { item.type = type; return this; }
        public Builder name(String name) { item.name = name; return this; }
        public Builder about(String about) { item.about = about; return this; }
        public Builder quantity(long qty) { item.quantity = qty; return this; }
        public Builder price(Price price) { item.price = price; return this; }
        public Builder reference(String ref) { item.reference = ref; return this; }
        public Builder taxCode(String tax) { item.taxCode = tax; return this; }
        public Builder customData(Map<String, String> data) { item.customData = data; return this; }
        public ProductLineItem build() { return item; }
    }
}
