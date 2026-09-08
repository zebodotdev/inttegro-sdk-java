package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class MessageTemplateSafetyResult {
    @JsonProperty("content_hash") public String contentHash;
    public List<MessageTemplateScannedLink> links;
    @JsonProperty("normalized_text") public String normalizedText;
    @JsonProperty("quarantine_notes") public String quarantineNotes;
    @JsonProperty("reason_codes") public List<String> reasonCodes;
    @JsonProperty("sanitized_html") public String sanitizedHtml;
    public String scanner;
    public ContentSafetyStatus status;
}
