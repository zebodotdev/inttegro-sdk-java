package com.inttegro.messages;

import java.util.List;

public final class CreateMessageTemplateParams {
    public String name;
    public String about;
    public MessageTemplateChannel channel;
    public String purpose;
    public String locale;
    public List<MessageTemplateVariable> variables;
    public MessageTemplateSmsContent sms;
    public MessageTemplateEmailContent email;
    public List<String> attachments;
}
