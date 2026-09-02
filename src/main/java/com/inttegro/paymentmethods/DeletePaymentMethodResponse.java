package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class DeletePaymentMethodResponse {
    public boolean deleted;
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
}
