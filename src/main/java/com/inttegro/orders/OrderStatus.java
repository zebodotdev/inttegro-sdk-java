package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {
    @JsonProperty("preparing") PREPARING,
    @JsonProperty("requires_payment") REQUIRES_PAYMENT,
    @JsonProperty("paid") PAID,
    @JsonProperty("completed") COMPLETED,
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("unknown") UNKNOWN
}
