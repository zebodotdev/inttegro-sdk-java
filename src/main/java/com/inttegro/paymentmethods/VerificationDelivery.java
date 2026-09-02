package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class VerificationDelivery {
    public String recipient;
    public String channel;
    @JsonProperty("sender_id")
    public String senderId;
}
