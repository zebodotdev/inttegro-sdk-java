package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ContentSafetyStatus {
    @JsonProperty("allowed") ALLOWED,
    @JsonProperty("rejected") REJECTED,
    @JsonProperty("quarantined") QUARANTINED
}
