package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MessageTemplateEmailContent {
    public String subject;
    public String html;
    public MessageTemplateMailbox from;
    @JsonProperty("reply_to") public MessageTemplateMailbox replyTo;
    public MessageHeaders headers;
}
