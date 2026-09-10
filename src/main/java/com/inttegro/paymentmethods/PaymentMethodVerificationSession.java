package com.inttegro.paymentmethods;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PaymentMethodVerificationSession {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
    public String status;
    @JsonProperty("token_sent_at")
    public OffsetDateTime tokenSentAt;
    @JsonProperty("expires_at")
    public OffsetDateTime expiresAt;
    public VerificationDelivery delivery;
}
