package com.inttegro.keys;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKeyUsageRow {
    @JsonProperty("secret_key_id")
    public String secretKeyId;
    @JsonProperty("occurred_at")
    public OffsetDateTime occurredAt;
    @JsonProperty("auth_result")
    public SecretKeyAuthResult authResult;
}
