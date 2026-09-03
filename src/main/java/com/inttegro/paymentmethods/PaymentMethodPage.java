package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PaymentMethodPage {
    public Integer number;
    public Integer size;
    @JsonProperty("payment_methods")
    public java.util.List<PaymentMethod> paymentMethods;
}
