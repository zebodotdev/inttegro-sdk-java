package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SecretKeyStatus {
    @JsonProperty("active") ACTIVE,
    @JsonProperty("revoked") REVOKED,
    @JsonProperty("expired") EXPIRED
}
