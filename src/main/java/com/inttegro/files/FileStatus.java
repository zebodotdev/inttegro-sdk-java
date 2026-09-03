package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FileStatus {
    @JsonProperty("uploading") UPLOADING,
    @JsonProperty("processing") PROCESSING,
    @JsonProperty("available") AVAILABLE,
    @JsonProperty("failed") FAILED,
    @JsonProperty("deleted") DELETED
}
