package com.inttegro.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChimeRecipientType {
    @JsonProperty("phone")
    PHONE,
    @JsonProperty("email")
    EMAIL
}
