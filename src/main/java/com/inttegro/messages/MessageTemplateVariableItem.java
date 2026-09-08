package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.JsonValue;

public final class MessageTemplateVariableItem {
    public String name;
    public MessageTemplateVariableItemType type;
    public Boolean required;
    @JsonProperty("default") public JsonValue defaultValue;
    public String about;
}
