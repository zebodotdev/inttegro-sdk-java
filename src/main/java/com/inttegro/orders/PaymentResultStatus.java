package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Result states returned while executing or confirming a payment. */
public enum PaymentResultStatus {
    @JsonProperty("pending") PENDING,
    @JsonProperty("requires_confirmation") REQUIRES_CONFIRMATION,
    @JsonProperty("processing") PROCESSING,
    @JsonProperty("succeeded") SUCCEEDED,
    @JsonProperty("failed") FAILED
}
