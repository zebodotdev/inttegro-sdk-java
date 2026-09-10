package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PurchaseIntentVariant {
    public boolean active;
    public Integer position;
    public PurchaseIntentPrice price;
    public PurchaseIntentProduct product;
    @JsonProperty("product_id")
    public String productId;
    @JsonProperty("variant_values")
    public Map<String, String> variantValues;
}
