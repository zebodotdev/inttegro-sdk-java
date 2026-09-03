package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentAttemptStatus {
    @JsonProperty("initiated") INITIATED,
    @JsonProperty("executed") EXECUTED,
    @JsonProperty("succeeded") SUCCEEDED,
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("failed") FAILED,
    @JsonProperty("unknown") UNKNOWN
}
