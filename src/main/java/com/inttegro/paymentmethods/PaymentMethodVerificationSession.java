package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodVerificationSession {
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
    public String status;
    @JsonProperty("token_sent_at")
    public String tokenSentAt;
    @JsonProperty("expires_at")
    public String expiresAt;
    public VerificationDelivery delivery;
}
