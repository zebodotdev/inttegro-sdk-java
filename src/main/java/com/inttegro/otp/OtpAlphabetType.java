package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OtpAlphabetType {
    @JsonProperty("numeric") NUMERIC,
    @JsonProperty("alpha") ALPHA,
    @JsonProperty("alphanumeric") ALPHANUMERIC
}
