package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class OtpVerification {
    public OtpTransaction transaction;
    @JsonProperty("verification_attempt") public Map<String, Object> verificationAttempt;
}
