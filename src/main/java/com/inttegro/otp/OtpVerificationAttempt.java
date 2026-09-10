package com.inttegro.otp;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class OtpVerificationAttempt {
    public String id;
    public String recipient;
    @JsonProperty("presented_token") public String presentedToken;
    @JsonProperty("attempted_at") public OffsetDateTime attemptedAt;
    public OtpVerificationResult result;
}
