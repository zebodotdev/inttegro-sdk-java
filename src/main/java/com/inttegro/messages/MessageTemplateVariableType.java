package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MessageTemplateVariableType {
    @JsonProperty("string") STRING,
    @JsonProperty("number") NUMBER,
    @JsonProperty("integer") INTEGER,
    @JsonProperty("boolean") BOOLEAN,
    @JsonProperty("url") URL,
    @JsonProperty("email") EMAIL,
    @JsonProperty("phone") PHONE,
    @JsonProperty("date") DATE,
    @JsonProperty("datetime") DATETIME,
    @JsonProperty("array") ARRAY
}
