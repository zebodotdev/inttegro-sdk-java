package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKeyUsageParams {
    @JsonProperty("secret_key_id")
    public String secretKeyId;
    public Integer page;
    public Integer number;
    public Integer size;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final SecretKeyUsageParams params = new SecretKeyUsageParams();
        public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
        public Builder page(Integer page) { params.page = page; return this; }
        public Builder number(Integer number) { params.number = number; return this; }
        public Builder size(Integer size) { params.size = size; return this; }
        public SecretKeyUsageParams build() { return params; }
    }
}
