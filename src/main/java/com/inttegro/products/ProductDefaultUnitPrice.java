package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductDefaultUnitPrice {
    public String id;
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public ProductPriceAmount nominal;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("archived_at")
    public String archivedAt;
}
