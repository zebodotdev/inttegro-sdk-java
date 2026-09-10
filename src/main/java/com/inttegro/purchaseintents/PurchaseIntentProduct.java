package com.inttegro.purchaseintents;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.CustomData;
import com.inttegro.products.ProductAttribute;
import com.inttegro.products.ProductDimensions;
import com.inttegro.products.ProductMedia;
import com.inttegro.products.ProductPriceSummary;
import com.inttegro.products.ProductShipment;
import com.inttegro.products.ProductType;
import java.util.List;

public class PurchaseIntentProduct {
    public String id;
    public String about;
    public boolean active;
    @JsonProperty("archived_at")
    public OffsetDateTime archivedAt;
    public List<ProductAttribute> attributes;
    public String category;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("custom_data")
    public CustomData customData;
    public String description;
    public ProductDimensions dimensions;
    public ProductMedia media;
    public String name;
    public List<ProductPriceSummary> prices;
    @JsonProperty("published_at")
    public OffsetDateTime publishedAt;
    public String reference;
    public ProductShipment shipment;
    @JsonProperty("tax_code")
    public String taxCode;
    public ProductType type;
    @JsonProperty("unit_dim")
    public String unitDim;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    @JsonProperty("variant_set_id")
    public String variantSetId;
}
