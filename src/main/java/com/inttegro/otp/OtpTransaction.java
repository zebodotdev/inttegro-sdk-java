package com.inttegro.otp;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class OtpTransaction {
    public String id;
    public OtpStatus status;
    @JsonProperty("full_message") public String fullMessage;
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    @JsonProperty("cancel_reason") public String cancelReason;
    public OtpTransmission transmission;
}
