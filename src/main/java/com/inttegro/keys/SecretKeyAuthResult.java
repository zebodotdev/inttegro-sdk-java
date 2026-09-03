package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SecretKeyAuthResult {
    @JsonProperty("succeeded") SUCCEEDED,
    @JsonProperty("failed") FAILED
}
