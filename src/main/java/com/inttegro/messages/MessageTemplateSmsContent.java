package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MessageTemplateSmsContent {
    @JsonProperty("message_template") public String messageTemplate;
}
