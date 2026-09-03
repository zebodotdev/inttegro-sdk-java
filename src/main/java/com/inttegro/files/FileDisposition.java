package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileDisposition {
    @JsonProperty("attachment") ATTACHMENT,
    @JsonProperty("inline") INLINE
}
