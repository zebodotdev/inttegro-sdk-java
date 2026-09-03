package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileLinkStatus {
    @JsonProperty("active") ACTIVE,
    @JsonProperty("revoked") REVOKED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("disabled") DISABLED
}
