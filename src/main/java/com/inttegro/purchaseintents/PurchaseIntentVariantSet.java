package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PurchaseIntentVariantSet {
    public boolean active;
    @JsonProperty("default_product_id")
    public String defaultProductId;
    public String description;
    public String id;
    public String name;
    public String reference;
    @JsonProperty("variant_axes")
    public List<PurchaseIntentVariantAxis> variantAxes;
    public List<PurchaseIntentVariant> variants;
}
