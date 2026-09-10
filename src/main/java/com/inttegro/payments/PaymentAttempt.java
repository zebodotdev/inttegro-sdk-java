package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentAttempt {
    @JsonProperty("payment_method_type") public String paymentMethodType;
    @JsonProperty("payment_method_id") public String paymentMethodId;
    public PaymentAttemptError error;
    public String reference;
    public PaymentAttemptStatus status;
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("succeeded_at") public OffsetDateTime succeededAt;
}
