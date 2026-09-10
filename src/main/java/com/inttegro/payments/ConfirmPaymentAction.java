package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmPaymentAction {
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    public String scheme;
    public ConfirmationRequest request;
    public ConfirmationAttempt attempt;
    public boolean confirmed;
    public String status;
}
