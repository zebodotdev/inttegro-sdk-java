package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChimeRecipientType {
    @JsonProperty("phone") PHONE,
    @JsonProperty("email") EMAIL
}
