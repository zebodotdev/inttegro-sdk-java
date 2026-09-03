package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PurchaseIntentStatus {
    @JsonProperty("active") ACTIVE,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("inactive") INACTIVE,
    @JsonProperty("used") USED
}
