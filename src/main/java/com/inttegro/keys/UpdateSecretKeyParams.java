package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UpdateSecretKeyParams {
    @JsonProperty("secret_key_id")
    public String secretKeyId;
    public String label;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UpdateSecretKeyParams params = new UpdateSecretKeyParams();
        public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
        public Builder label(String label) { params.label = label; return this; }
        public UpdateSecretKeyParams build() { return params; }
    }
}
