package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MobileMoneyNetwork {
    @JsonProperty("airtel") AIRTEL,
    @JsonProperty("mtn") MTN,
    @JsonProperty("telecel") TELECEL,
    @JsonProperty("vodafone") VODAFONE
}
