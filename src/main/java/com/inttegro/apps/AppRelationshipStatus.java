package com.inttegro.apps;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AppRelationshipStatus {
    @JsonProperty("active") ACTIVE,
    @JsonProperty("inactive") INACTIVE,
    @JsonProperty("suspended") SUSPENDED,
    @JsonProperty("revoked") REVOKED
}
