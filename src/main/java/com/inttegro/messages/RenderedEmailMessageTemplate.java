package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RenderedEmailMessageTemplate {
    public String subject;
    public String text;
    public String html;
    public MessageTemplateMailbox from;
    @JsonProperty("reply_to") public MessageTemplateMailbox replyTo;
    public MessageHeaders headers;
    public MessageTemplateSafetyResult safety;
}
