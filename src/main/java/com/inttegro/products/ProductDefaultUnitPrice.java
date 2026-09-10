package com.inttegro.products;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import java.util.List;
import java.util.Map;

public class ProductDefaultUnitPrice {
    public String id;
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public Amount nominal;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    @JsonProperty("archived_at")
    public OffsetDateTime archivedAt;
}
