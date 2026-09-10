package com.inttegro.products;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Product {
    public String id;
    public ProductType type;
    public String reference;
    public String name;
    public String description;
    public String about;
    @JsonProperty("tax_code")
    public String taxCode;
    public String category;
    public List<ProductPriceSummary> prices;
    public ProductShipment shipment;
    public ProductDimensions dimensions;
    @JsonProperty("unit_dim") public String unitDim;
    public ProductMedia media;
    public List<ProductAttribute> attributes;
    @JsonProperty("custom_data")
    public CustomData customData;
    public Boolean active;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    @JsonProperty("archived_at")
    public OffsetDateTime archivedAt;
    @JsonProperty("published_at")
    public OffsetDateTime publishedAt;
}
