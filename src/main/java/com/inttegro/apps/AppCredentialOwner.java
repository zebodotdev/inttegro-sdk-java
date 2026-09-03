package com.inttegro.apps;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AppCredentialOwner {
    @JsonProperty("child") CHILD,
    @JsonProperty("parent") PARENT
}
