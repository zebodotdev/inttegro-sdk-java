package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductType {
    @JsonProperty("physical") PHYSICAL,
    @JsonProperty("digital") DIGITAL,
    @JsonProperty("service") SERVICE,
    @JsonProperty("voucher") VOUCHER,
    @JsonProperty("custom") CUSTOM,
    @JsonProperty("cause") CAUSE
}
