package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.JsonData;

public final class MessageTemplateReference {
    @JsonProperty("template_id") public String templateId;
    public JsonData variables;
}
