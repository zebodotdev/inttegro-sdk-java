package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GeneratedSecretKey {
    public String id;
    public String label;
    @JsonProperty("token_type")
    public SecretKeyTokenType tokenType;
    @JsonProperty("issued_at")
    public String issuedAt;
    public String token;
}
