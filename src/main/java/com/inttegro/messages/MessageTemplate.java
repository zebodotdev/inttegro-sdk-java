package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class MessageTemplate {
    public String id;
    public String name;
    public String about;
    public MessageTemplateChannel channel;
    public String purpose;
    public String locale;
    public MessageTemplateStatus status;
    public Integer version;
    @JsonProperty("published_version") public Integer publishedVersion;
    @JsonProperty("draft_version") public Integer draftVersion;
    @JsonProperty("has_unpublished_changes") public Boolean hasUnpublishedChanges;
    public MessageTemplateSmsContent sms;
    public MessageTemplateEmailContent email;
    public List<MessageTemplateVariable> variables;
    public List<String> attachments;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("updated_at") public String updatedAt;
    @JsonProperty("published_at") public String publishedAt;
    @JsonProperty("archived_at") public String archivedAt;
}
