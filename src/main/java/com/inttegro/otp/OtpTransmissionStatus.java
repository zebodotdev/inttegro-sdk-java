package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OtpTransmissionStatus {
    @JsonProperty("delivered") DELIVERED,
    @JsonProperty("failed") FAILED,
    @JsonProperty("submitted") SUBMITTED
}
