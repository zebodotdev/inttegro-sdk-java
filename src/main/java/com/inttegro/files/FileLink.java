package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class FileLink {
    public String id;
    @JsonProperty("file_id") public String fileId;
    public String status;
    @JsonProperty("expires_at") public String expiresAt;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("revoked_at") public String revokedAt;
    @JsonProperty("custom_data") public Map<String, String> customData;
}
