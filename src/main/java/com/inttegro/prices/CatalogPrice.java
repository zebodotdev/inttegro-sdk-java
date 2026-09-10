package com.inttegro.prices;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import com.inttegro.products.Product;

/** A stored price resource in the product catalog. */
public class CatalogPrice {
    public String id;
    public String label;
    public String about;
    public Boolean active;
    public Amount nominal;
    @JsonProperty("product_id")
    public String productId;
    public Product product;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    @JsonProperty("archived_at")
    public OffsetDateTime archivedAt;
}
