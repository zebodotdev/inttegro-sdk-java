package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PaymentMethodDeletion {
    public boolean deleted;
    @JsonProperty("payment_method_id") public String paymentMethodId;
}
