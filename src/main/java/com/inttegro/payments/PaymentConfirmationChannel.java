package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentConfirmationChannel {
    @JsonProperty("sms") SMS,
    @JsonProperty("email") EMAIL,
    @JsonProperty("push") PUSH
}
