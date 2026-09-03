package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKey {
    public String id;
    public String label;
    @JsonProperty("token_type")
    public SecretKeyTokenType tokenType;
    @JsonProperty("issued_at")
    public String issuedAt;
    @JsonProperty("updated_at")
    public String updatedAt;
    @JsonProperty("expires_at")
    public String expiresAt;
    public SecretKeyStatus status;
    public Boolean active;
    @JsonProperty("revoked_at")
    public String revokedAt;
    @JsonProperty("last_used_at")
    public String lastUsedAt;
    @JsonProperty("usage_count")
    public Integer usageCount;
}
