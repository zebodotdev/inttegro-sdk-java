package com.inttegro.messages;

import java.util.List;

public final class RenderedMessageTemplate {
    public MessageTemplateChannel channel;
    public List<String> attachments;
    public RenderedSmsMessageTemplate sms;
    public RenderedEmailMessageTemplate email;
}
