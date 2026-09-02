package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DestroySecretKeyParams {
    @JsonProperty("secret_key_id")
    public String secretKeyId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final DestroySecretKeyParams params = new DestroySecretKeyParams();
        public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
        public DestroySecretKeyParams build() { return params; }
    }
}
