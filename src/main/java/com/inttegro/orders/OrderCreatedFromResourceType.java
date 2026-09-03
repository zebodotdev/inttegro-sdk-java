package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderCreatedFromResourceType {
    @JsonProperty("purchase_intent") PURCHASE_INTENT
}
