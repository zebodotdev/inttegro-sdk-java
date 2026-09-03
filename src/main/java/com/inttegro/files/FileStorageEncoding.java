package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileStorageEncoding {
    @JsonProperty("identity") IDENTITY,
    @JsonProperty("br") BROTLI
}
