package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UploadRequestStatus {
    @JsonProperty("pending") PENDING,
    @JsonProperty("uploading") UPLOADING,
    @JsonProperty("fulfilled") FULFILLED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("failed") FAILED
}
