package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileScanStatus {
    @JsonProperty("pending") PENDING,
    @JsonProperty("passed") PASSED,
    @JsonProperty("failed") FAILED,
    @JsonProperty("skipped") SKIPPED
}
