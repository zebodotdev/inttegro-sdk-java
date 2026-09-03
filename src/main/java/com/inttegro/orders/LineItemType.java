package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LineItemType {
    @JsonProperty("product") PRODUCT,
    @JsonProperty("fee") FEE,
    @JsonProperty("shipping") SHIPPING
}
