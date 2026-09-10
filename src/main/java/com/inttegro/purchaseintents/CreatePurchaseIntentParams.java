package com.inttegro.purchaseintents;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePurchaseIntentParams {
    public PurchaseIntentProductSelector product;
    @JsonProperty("product_id")
    public String productId;
    public PurchaseIntentPriceSelector price;
    @JsonProperty("price_id")
    public String priceId;
    public PurchaseIntentQuantity quantity;
    public PurchaseIntentUsage usage;
    @JsonProperty("expires_at")
    public OffsetDateTime expiresAt;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreatePurchaseIntentParams params = new CreatePurchaseIntentParams();
        public Builder product(PurchaseIntentProductSelector product) { params.product = product; return this; }
        public Builder productId(String productId) { params.productId = productId; return this; }
        public Builder price(PurchaseIntentPriceSelector price) { params.price = price; return this; }
        public Builder priceId(String priceId) { params.priceId = priceId; return this; }
        public Builder quantity(PurchaseIntentQuantity quantity) { params.quantity = quantity; return this; }
        public Builder usage(PurchaseIntentUsage usage) { params.usage = usage; return this; }
        public Builder expiresAt(OffsetDateTime expiresAt) { params.expiresAt = expiresAt; return this; }
        public CreatePurchaseIntentParams build() { return params; }
    }
}
