package com.inttegro.files;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class UploadRequest {
    public String id;
    public String purpose;
    public UploadRequestStatus status;
    @JsonProperty("upload_url") public String uploadUrl;
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    @JsonProperty("custom_data") public CustomData customData;
    public FileMetadata metadata;
    public UploadConstraints constraints;
    public UploadDisplay display;
    public UploadAttempts attempts;
}
