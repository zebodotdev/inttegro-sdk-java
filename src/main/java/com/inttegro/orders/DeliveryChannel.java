package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeliveryChannel {
    @JsonProperty("email") EMAIL,
    @JsonProperty("sms") SMS
}
