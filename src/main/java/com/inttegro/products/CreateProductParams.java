package com.inttegro.products;

import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CreateProductParams {
    public ProductType type;
    public String reference;
    public String name;
    public String description;
    public String about;
    @JsonProperty("tax_code")
    public String taxCode;
    public ProductCategory category;
    public ProductShipment shipment;
    public ProductDimensions dimensions;
    @JsonProperty("unit_dimension") public String unitDimension;
    public ProductMedia media;
    public List<ProductAttribute> attributes;
    public Boolean publish;
    @JsonProperty("custom_data")
    public CustomData customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateProductParams params = new CreateProductParams();
        public Builder type(ProductType type) { params.type = type; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder name(String name) { params.name = name; return this; }
        public Builder description(String description) { params.description = description; return this; }
        public Builder about(String about) { params.about = about; return this; }
        public Builder taxCode(String taxCode) { params.taxCode = taxCode; return this; }
        public Builder category(ProductCategory category) { params.category = category; return this; }
        public Builder shipment(ProductShipment shipment) { params.shipment = shipment; return this; }
        public Builder dimensions(ProductDimensions dimensions) { params.dimensions = dimensions; return this; }
        public Builder unitDimension(String unitDimension) { params.unitDimension = unitDimension; return this; }
        public Builder media(ProductMedia media) { params.media = media; return this; }
        public Builder attributes(List<ProductAttribute> attributes) { params.attributes = attributes; return this; }
        public Builder publish(Boolean publish) { params.publish = publish; return this; }
        public Builder customData(CustomData customData) { params.customData = customData; return this; }
        public CreateProductParams build() { return params; }
    }
}
