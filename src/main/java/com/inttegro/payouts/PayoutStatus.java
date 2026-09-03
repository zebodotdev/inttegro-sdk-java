package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PayoutStatus {
    @JsonProperty("initialized") INITIALIZED,
    @JsonProperty("scheduled") SCHEDULED,
    @JsonProperty("processing") PROCESSING,
    @JsonProperty("executing") EXECUTING,
    @JsonProperty("succeeded") SUCCEEDED,
    @JsonProperty("invalid") INVALID,
    @JsonProperty("canceled") CANCELED
}
