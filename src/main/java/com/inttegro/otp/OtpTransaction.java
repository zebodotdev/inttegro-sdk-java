package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OtpTransaction {
    public String id;
    public OtpStatus status;
    @JsonProperty("full_message") public String fullMessage;
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("expires_at") public String expiresAt;
    @JsonProperty("canceled_at") public String canceledAt;
    @JsonProperty("cancel_reason") public String cancelReason;
    public OtpTransmission transmission;
}
