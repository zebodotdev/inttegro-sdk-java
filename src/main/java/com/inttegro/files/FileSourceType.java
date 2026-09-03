package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileSourceType {
    @JsonProperty("direct") DIRECT,
    @JsonProperty("upload_request") UPLOAD_REQUEST,
    @JsonProperty("service") SERVICE
}
