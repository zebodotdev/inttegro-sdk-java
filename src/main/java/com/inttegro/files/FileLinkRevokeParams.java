package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileLinkRevokeParams {
    public String id;
    @JsonProperty("revoked_by")
    public Actor revokedBy;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileLinkRevokeParams params = new FileLinkRevokeParams();
        public Builder id(String id) { params.id = id; return this; }
        public Builder revokedBy(Actor revokedBy) { params.revokedBy = revokedBy; return this; }
        public FileLinkRevokeParams build() { return params; }
    }
}
