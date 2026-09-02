package com.inttegro.prices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {
    public String id;
    @JsonProperty("product_id")
    public String productId;
    public String label;
    public String about;
    public PriceNominal nominal;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("archived_at")
    public String archivedAt;
}
