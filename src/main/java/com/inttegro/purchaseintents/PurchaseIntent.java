package com.inttegro.purchaseintents;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseIntent {
    public PurchaseIntentActivityLog activity;
    @JsonProperty("allow_variants")
    public boolean allowVariants;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    @JsonProperty("expires_at")
    public OffsetDateTime expiresAt;
    public String id;
    @JsonProperty("inactive_at")
    public OffsetDateTime inactiveAt;
    public PurchaseIntentMerchant merchant;
    public PurchaseIntentPrice price;
    public PurchaseIntentProduct product;
    public PurchaseIntentQuantity quantity;
    public PurchaseIntentStatus status;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    public PurchaseIntentUsage usage;
    @JsonProperty("variant_set")
    public PurchaseIntentVariantSet variantSet;

    /** Whether the purchase intent is currently active. */
    public boolean isActive() { return status == PurchaseIntentStatus.ACTIVE; }

    /** Whether the purchase intent can create at most one order. */
    public boolean isSingleUse() {
        return usage != null && Boolean.TRUE.equals(usage.singleUse);
    }

    /** ID of the order that consumed a single-use purchase intent. */
    public String usedOrderId() {
        if (!isSingleUse() || usage.order == null || usage.order.id == null || usage.order.id.isEmpty()) {
            return null;
        }
        return usage.order.id;
    }
}
