package com.inttegro.products;

import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Product {
    public String id;
    @JsonProperty("application_id")
    public String applicationId;
    public ProductType type;
    public String reference;
    public String name;
    public String description;
    public String about;
    @JsonProperty("tax_code")
    public String taxCode;
    public ProductCategory category;
    @JsonProperty("default_unit_price")
    public ProductDefaultUnitPrice defaultUnitPrice;
    public List<ProductPriceSummary> prices;
    public ProductShipment shipment;
    public ProductDimensions dimensions;
    @JsonProperty("unit_dimension") public String unitDimension;
    public ProductMedia media;
    public List<ProductAttribute> attributes;
    @JsonProperty("custom_data")
    public CustomData customData;
    public Boolean active;
    public Boolean archived;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("archived_at")
    public String archivedAt;
}
