package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public final class MessageTemplate {
    public String id;
    public String name;
    public String about;
    public MessageTemplateChannel channel;
    public String purpose;
    public String locale;
    public MessageTemplateStatus status;
    public Map<String, Object> sms;
    public Map<String, Object> email;
    public List<Map<String, Object>> variables;
    public List<String> attachments;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("updated_at") public String updatedAt;
    @JsonProperty("published_at") public String publishedAt;
    @JsonProperty("archived_at") public String archivedAt;
}
