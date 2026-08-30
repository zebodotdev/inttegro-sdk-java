package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KeyModels {
    public static class GenerateSecretKeyParams {
        public String label;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final GenerateSecretKeyParams params = new GenerateSecretKeyParams();
            public Builder label(String label) { params.label = label; return this; }
            public GenerateSecretKeyParams build() { return params; }
        }
    }

    public static class LookupSecretKeyParams {
        @JsonProperty("secret_key_id")
        public String secretKeyId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupSecretKeyParams params = new LookupSecretKeyParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public LookupSecretKeyParams build() { return params; }
        }
    }

    public static class PageSecretKeysParams {
        public Integer page;
        public Integer number;
        public Integer size;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PageSecretKeysParams params = new PageSecretKeysParams();
            public Builder page(Integer page) { params.page = page; return this; }
            public Builder number(Integer number) { params.number = number; return this; }
            public Builder size(Integer size) { params.size = size; return this; }
            public PageSecretKeysParams build() { return params; }
        }
    }

    public static class UpdateSecretKeyParams {
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

    public static class DestroySecretKeyParams {
        @JsonProperty("secret_key_id")
        public String secretKeyId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final DestroySecretKeyParams params = new DestroySecretKeyParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public DestroySecretKeyParams build() { return params; }
        }
    }

    public static class SecretKeyUsageParams {
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

    public static class SecretKey {
        public String id;
        public String label;
        @JsonProperty("token_type")
        public String tokenType;
        @JsonProperty("issued_at")
        public String issuedAt;
        @JsonProperty("updated_at")
        public String updatedAt;
        @JsonProperty("expires_at")
        public String expiresAt;
        public String status;
        public Boolean active;
        @JsonProperty("revoked_at")
        public String revokedAt;
        @JsonProperty("last_used_at")
        public String lastUsedAt;
        @JsonProperty("usage_count")
        public Integer usageCount;
    }

    public static class GeneratedSecretKey {
        public String id;
        public String label;
        @JsonProperty("token_type")
        public String tokenType;
        @JsonProperty("issued_at")
        public String issuedAt;
        public String token;
    }

    public static class SecretKeyPage {
        public Integer number;
        public Integer size;
        public Integer count;
        public Integer total;
        @JsonProperty("has_more")
        public Boolean hasMore;
        public List<SecretKey> keys;
    }

    public static class SecretKeyUsageRow {
        @JsonProperty("secret_key_id")
        public String secretKeyId;
        @JsonProperty("occurred_at")
        public String occurredAt;
        @JsonProperty("auth_result")
        public String authResult;
    }

    public static class SecretKeyUsagePage {
        public Integer number;
        public Integer size;
        public Integer count;
        public Integer total;
        @JsonProperty("has_more")
        public Boolean hasMore;
        public List<SecretKeyUsageRow> rows;
    }

    public static class GenerateSecretKeyResponse {
        public GeneratedSecretKey key;
    }

    public static class LookupSecretKeyResponse {
        public SecretKey key;
    }

    public static class PageSecretKeysResponse {
        public SecretKeyPage page;
    }

    public static class UpdateSecretKeyResponse {
        public SecretKey key;
    }

    public static class DestroySecretKeyResponse {
        public SecretKey key;
    }

    public static class SecretKeyUsageResponse {
        public SecretKey key;
        public SecretKeyUsagePage usage;
    }
}
