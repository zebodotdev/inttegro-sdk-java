package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChimeTransport {
    @JsonProperty("sms") SMS,
    @JsonProperty("email") EMAIL
}
