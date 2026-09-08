package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RenderedSmsMessageTemplate {
    @JsonProperty("full_message") public String fullMessage;
}
