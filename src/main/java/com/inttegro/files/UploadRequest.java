package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class UploadRequest {
    public String id;
    public String purpose;
    public String status;
    @JsonProperty("upload_url") public String uploadUrl;
    @JsonProperty("expires_at") public String expiresAt;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("canceled_at") public String canceledAt;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public Map<String, String> metadata;
    public UploadConstraints constraints;
    public UploadDisplay display;
    public UploadAttempts attempts;
}
