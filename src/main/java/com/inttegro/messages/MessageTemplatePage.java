package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class MessageTemplatePage {
    public Integer number;
    public Integer size;
    @JsonProperty("message_templates") public List<MessageTemplate> messageTemplates;
}
