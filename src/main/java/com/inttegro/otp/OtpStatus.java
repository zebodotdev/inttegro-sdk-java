package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OtpStatus {
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("expired") EXPIRED,
    @JsonProperty("pending") PENDING,
    @JsonProperty("pending_delivery") PENDING_DELIVERY,
    @JsonProperty("pending_verification") PENDING_VERIFICATION,
    @JsonProperty("verified") VERIFIED
}
