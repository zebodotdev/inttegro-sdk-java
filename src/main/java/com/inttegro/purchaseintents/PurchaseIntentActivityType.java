package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PurchaseIntentActivityType {
    @JsonProperty("expired_viewed") EXPIRED_VIEWED,
    @JsonProperty("order_created") ORDER_CREATED,
    @JsonProperty("payment_failed") PAYMENT_FAILED,
    @JsonProperty("payment_started") PAYMENT_STARTED,
    @JsonProperty("viewed") VIEWED
}
