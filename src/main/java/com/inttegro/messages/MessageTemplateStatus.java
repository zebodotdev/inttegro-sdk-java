package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MessageTemplateStatus {
    @JsonProperty("draft") DRAFT,
    @JsonProperty("published") PUBLISHED,
    @JsonProperty("archived") ARCHIVED
}
