package com.inttegro.money;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Currency identifiers use conventional uppercase names and lowercase wire values. */
public enum Currency {
    @JsonProperty("ghs") GHS,
    @JsonProperty("usd") USD,
    @JsonProperty("gbp") GBP,
    @JsonProperty("eur") EUR,
    @JsonProperty("cny") CNY
}
