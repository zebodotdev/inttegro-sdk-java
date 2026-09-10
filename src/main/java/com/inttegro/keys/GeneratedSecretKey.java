package com.inttegro.keys;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GeneratedSecretKey {
    public String id;
    public String label;
    @JsonProperty("token_type")
    public SecretKeyTokenType tokenType;
    @JsonProperty("issued_at")
    public OffsetDateTime issuedAt;
    public String token;
}
