package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OtpVerificationVerdict {
    @JsonProperty("fail") FAIL,
    @JsonProperty("pass") PASS
}
