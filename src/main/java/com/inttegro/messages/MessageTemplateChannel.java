package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MessageTemplateChannel {
    @JsonProperty("sms") SMS,
    @JsonProperty("email") EMAIL
}
