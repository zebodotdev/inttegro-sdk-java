package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OtpTransmission {
    public String recipient;
    @JsonProperty("sender_id") public String senderId;
    @JsonProperty("sent_at") public String sentAt;
    @JsonProperty("sent_via") public String sentVia;
    public OtpTransmissionStatus status;
}
