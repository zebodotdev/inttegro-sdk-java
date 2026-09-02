package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileLinkCreateParams {
    public FileLinkAccess access;
    @JsonProperty("created_by")
    public Actor createdBy;
    public FileLinkDelivery delivery;
    @JsonProperty("expires_at")
    public String expiresAt;
    @JsonProperty("file_id")
    public String fileId;
    @JsonProperty("custom_data")
    public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileLinkCreateParams params = new FileLinkCreateParams();
        public Builder access(FileLinkAccess access) { params.access = access; return this; }
        public Builder createdBy(Actor createdBy) { params.createdBy = createdBy; return this; }
        public Builder delivery(FileLinkDelivery delivery) { params.delivery = delivery; return this; }
        public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
        public Builder fileId(String fileId) { params.fileId = fileId; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public FileLinkCreateParams build() { return params; }
    }
}
