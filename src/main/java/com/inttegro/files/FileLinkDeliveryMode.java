package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileLinkDeliveryMode {
    @JsonProperty("redirect") REDIRECT,
    @JsonProperty("download") DOWNLOAD,
    @JsonProperty("inline") INLINE
}
