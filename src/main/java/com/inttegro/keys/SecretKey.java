package com.inttegro.keys;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKey {
    public String id;
    public String label;
    @JsonProperty("token_type")
    public SecretKeyTokenType tokenType;
    @JsonProperty("issued_at")
    public OffsetDateTime issuedAt;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
    @JsonProperty("expires_at")
    public OffsetDateTime expiresAt;
    public SecretKeyStatus status;
    public Boolean active;
    @JsonProperty("revoked_at")
    public OffsetDateTime revokedAt;
    @JsonProperty("last_used_at")
    public OffsetDateTime lastUsedAt;
    @JsonProperty("usage_count")
    public Integer usageCount;
}
