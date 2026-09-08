package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MessageTemplatePreview {
    @JsonProperty("message_template") public MessageTemplate messageTemplate;
    public RenderedMessageTemplate rendered;
}
