package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.JsonValue;
import java.util.List;

public final class MessageTemplateVariable {
    public String name;
    public MessageTemplateVariableType type;
    public Boolean required;
    @JsonProperty("default") public JsonValue defaultValue;
    public String about;
    public List<MessageTemplateVariableItem> items;
}
