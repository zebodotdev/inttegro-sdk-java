package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CreateProductParams {
    public String type;
    public String reference;
    public String name;
    public String description;
    public String about;
    @JsonProperty("tax_code")
    public String taxCode;
    public ProductCategory category;
    public ProductPrice price;
    public ProductShipment shipment;
    public ProductMediaItem[] media;
    public Map<String, String> attributes;
    @JsonProperty("custom_data")
    public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateProductParams params = new CreateProductParams();
        public Builder type(String type) { params.type = type; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder name(String name) { params.name = name; return this; }
        public Builder description(String description) { params.description = description; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder taxCode(String taxCode) { params.taxCode = taxCode; return this; }
        public Builder category(ProductCategory category) { params.category = category; return this; }
        public Builder price(ProductPrice price) { params.price = price; return this; }
        public Builder shipment(ProductShipment shipment) { params.shipment = shipment; return this; }
        public Builder media(ProductMediaItem[] media) { params.media = media; return this; }
        public Builder attributes(Map<String, String> attributes) { params.attributes = attributes; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public CreateProductParams build() { return params; }
    }
}
