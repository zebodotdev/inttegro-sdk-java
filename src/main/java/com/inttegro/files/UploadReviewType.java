package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UploadReviewType {
    @JsonProperty("automatic") AUTOMATIC,
    @JsonProperty("manual") MANUAL
}
