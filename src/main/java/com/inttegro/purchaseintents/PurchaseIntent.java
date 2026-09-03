package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntent {
    public String id;
    @JsonProperty("application_id")
    public String applicationId;
    @JsonProperty("product_id")
    public String productId;
    @JsonProperty("price_id")
    public String priceId;
    public PurchaseIntentQuantity quantity;
    @JsonProperty("adjustable_quantity")
    public Boolean adjustableQuantity;
    @JsonProperty("allow_variants")
    public Boolean allowVariants;
    public PurchaseIntentStatus status;
    @JsonProperty("created_at")
    public String createdAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    public PurchaseIntentActivityLog activity;
    public com.inttegro.products.Product product;
    public com.inttegro.products.ProductPriceSummary price;
}
