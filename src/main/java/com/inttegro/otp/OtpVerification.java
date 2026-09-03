package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OtpVerification {
    public OtpTransaction transaction;
    @JsonProperty("verification_attempt") public OtpVerificationAttempt verificationAttempt;
}
