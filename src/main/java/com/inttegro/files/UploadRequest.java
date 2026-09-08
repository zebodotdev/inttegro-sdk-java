package com.inttegro.files;

import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class UploadRequest {
    public String id;
    public String purpose;
    public UploadRequestStatus status;
    @JsonProperty("upload_url") public String uploadUrl;
    @JsonProperty("expires_at") public String expiresAt;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("canceled_at") public String canceledAt;
    @JsonProperty("custom_data") public CustomData customData;
    public FileMetadata metadata;
    public UploadConstraints constraints;
    public UploadDisplay display;
    public UploadAttempts attempts;
}
