package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LookupSecretKeyParams {
    @JsonProperty("secret_key_id")
    public String secretKeyId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupSecretKeyParams params = new LookupSecretKeyParams();
        public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
        public LookupSecretKeyParams build() { return params; }
    }
}
