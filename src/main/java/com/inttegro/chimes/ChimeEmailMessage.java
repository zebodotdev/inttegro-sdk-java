package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.JsonData;
import com.inttegro.messages.MessageHeaders;
import com.inttegro.messages.MessageTemplateMailbox;
import com.inttegro.messages.MessageTemplateSafetyResult;

public final class ChimeEmailMessage {
    public String subject;
    public String text;
    public String html;
    public MessageTemplateMailbox from;
    @JsonProperty("reply_to") public MessageTemplateMailbox replyTo;
    public MessageHeaders headers;
    public MessageTemplateSafetyResult safety;
    public JsonData schema;
}
