package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class MessageTemplatePreview {
    @JsonProperty("message_template") public MessageTemplate messageTemplate;
    public Map<String, Object> rendered;
}
