package com.inttegro.apps;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AppManagementRole {
    @JsonProperty("parent") PARENT,
    @JsonProperty("child") CHILD
}
