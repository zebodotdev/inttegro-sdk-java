package com.inttegro.messages;

import java.time.OffsetDateTime;
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
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("updated_at") public OffsetDateTime updatedAt;
    @JsonProperty("published_at") public OffsetDateTime publishedAt;
    @JsonProperty("archived_at") public OffsetDateTime archivedAt;
}
