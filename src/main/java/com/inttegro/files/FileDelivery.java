package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileDelivery {
    @JsonProperty("stream") STREAM,
    @JsonProperty("redirect") REDIRECT
}
