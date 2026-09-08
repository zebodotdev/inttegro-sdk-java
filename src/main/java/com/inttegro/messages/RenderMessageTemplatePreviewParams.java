package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RenderMessageTemplatePreviewParams {
    @JsonProperty("message_template") public MessageTemplateReference messageTemplate;
}
