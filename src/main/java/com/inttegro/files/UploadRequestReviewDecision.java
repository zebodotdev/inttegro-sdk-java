package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UploadRequestReviewDecision {
    @JsonProperty("approved") APPROVED,
    @JsonProperty("rejected") REJECTED
}
