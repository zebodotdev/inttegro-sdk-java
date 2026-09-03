package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CheckoutPaymentStatus {
    @JsonProperty("requires_action") REQUIRES_ACTION,
    @JsonProperty("processing") PROCESSING,
    @JsonProperty("succeeded") SUCCEEDED,
    @JsonProperty("failed") FAILED,
    @JsonProperty("cancelled") CANCELLED
}
