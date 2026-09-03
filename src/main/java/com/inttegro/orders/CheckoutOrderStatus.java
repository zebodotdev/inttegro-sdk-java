package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CheckoutOrderStatus {
    @JsonProperty("preparing") PREPARING,
    @JsonProperty("requires_payment") REQUIRES_PAYMENT,
    @JsonProperty("completed") COMPLETED,
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("expired") EXPIRED
}
