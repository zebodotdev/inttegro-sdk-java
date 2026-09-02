package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKeyUsageRow {
    @JsonProperty("secret_key_id")
    public String secretKeyId;
    @JsonProperty("occurred_at")
    public String occurredAt;
    @JsonProperty("auth_result")
    public String authResult;
}
