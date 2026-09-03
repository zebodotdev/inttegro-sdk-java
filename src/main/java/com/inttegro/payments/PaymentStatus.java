package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Payment states returned by order resources. */
public enum PaymentStatus {
    @JsonProperty("initiated") INITIATED,
    @JsonProperty("requires_action") REQUIRES_ACTION,
    @JsonProperty("executed") EXECUTED,
    @JsonProperty("paid") PAID,
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("overdue") OVERDUE,
    @JsonProperty("failed") FAILED,
    @JsonProperty("unknown") UNKNOWN
}
