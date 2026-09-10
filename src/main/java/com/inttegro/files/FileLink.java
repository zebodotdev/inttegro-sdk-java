package com.inttegro.files;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class FileLink {
    public String id;
    @JsonProperty("file_id") public String fileId;
    public FileLinkStatus status;
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("revoked_at") public OffsetDateTime revokedAt;
    @JsonProperty("custom_data") public CustomData customData;
}
