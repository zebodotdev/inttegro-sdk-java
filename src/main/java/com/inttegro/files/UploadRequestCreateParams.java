package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadRequestCreateParams {
    public UploadAttempts attempts;
    public UploadConstraints constraints;
    public UploadDisplay display;
    @JsonProperty("expires_at")
    public String expiresAt;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    public String purpose;
    public Actor recipient;
    public Actor requester;
    public ResourceRef resource;
    public Actor subject;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadRequestCreateParams params = new UploadRequestCreateParams();
        public Builder attempts(UploadAttempts attempts) { params.attempts = attempts; return this; }
        public Builder constraints(UploadConstraints constraints) { params.constraints = constraints; return this; }
        public Builder display(UploadDisplay display) { params.display = display; return this; }
        public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder recipient(Actor recipient) { params.recipient = recipient; return this; }
        public Builder requester(Actor requester) { params.requester = requester; return this; }
        public Builder resource(ResourceRef resource) { params.resource = resource; return this; }
        public Builder subject(Actor subject) { params.subject = subject; return this; }
        public UploadRequestCreateParams build() { return params; }
    }
}
