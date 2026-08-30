package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KeyModels {
    public static class SecretKeyActorParams {
        public String service;
        @JsonProperty("user_id")
        public String userId;
        @JsonProperty("team_member_id")
        public String teamMemberId;
        public String email;
        public String name;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final SecretKeyActorParams params = new SecretKeyActorParams();
            public Builder service(String service) { params.service = service; return this; }
            public Builder userId(String userId) { params.userId = userId; return this; }
            public Builder teamMemberId(String teamMemberId) { params.teamMemberId = teamMemberId; return this; }
            public Builder email(String email) { params.email = email; return this; }
            public Builder name(String name) { params.name = name; return this; }
            public SecretKeyActorParams build() { return params; }
        }
    }

    public static class GenerateSecretKeyParams {
        public String label;
        @JsonProperty("user_agent")
        public String userAgent;
        @JsonProperty("remote_addr")
        public String remoteAddr;
        public SecretKeyActorParams actor;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final GenerateSecretKeyParams params = new GenerateSecretKeyParams();
            public Builder label(String label) { params.label = label; return this; }
            public Builder userAgent(String userAgent) { params.userAgent = userAgent; return this; }
            public Builder remoteAddr(String remoteAddr) { params.remoteAddr = remoteAddr; return this; }
            public Builder actor(SecretKeyActorParams actor) { params.actor = actor; return this; }
            public GenerateSecretKeyParams build() { return params; }
        }
    }

    public static class LookupSecretKeyParams {
        @JsonProperty("secret_key_id")
        public String secretKeyId;
        @JsonProperty("key_id")
        public String keyId;
        public String id;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupSecretKeyParams params = new LookupSecretKeyParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public Builder keyId(String keyId) { params.keyId = keyId; return this; }
            public Builder id(String id) { params.id = id; return this; }
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
        @JsonProperty("key_id")
        public String keyId;
        public String id;
        public String label;
        @JsonProperty("user_agent")
        public String userAgent;
        @JsonProperty("remote_addr")
        public String remoteAddr;
        public SecretKeyActorParams actor;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdateSecretKeyParams params = new UpdateSecretKeyParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public Builder keyId(String keyId) { params.keyId = keyId; return this; }
            public Builder id(String id) { params.id = id; return this; }
            public Builder label(String label) { params.label = label; return this; }
            public Builder userAgent(String userAgent) { params.userAgent = userAgent; return this; }
            public Builder remoteAddr(String remoteAddr) { params.remoteAddr = remoteAddr; return this; }
            public Builder actor(SecretKeyActorParams actor) { params.actor = actor; return this; }
            public UpdateSecretKeyParams build() { return params; }
        }
    }

    public static class DestroySecretKeyParams {
        @JsonProperty("secret_key_id")
        public String secretKeyId;
        @JsonProperty("key_id")
        public String keyId;
        public String id;
        @JsonProperty("user_agent")
        public String userAgent;
        @JsonProperty("remote_addr")
        public String remoteAddr;
        public SecretKeyActorParams actor;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final DestroySecretKeyParams params = new DestroySecretKeyParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public Builder keyId(String keyId) { params.keyId = keyId; return this; }
            public Builder id(String id) { params.id = id; return this; }
            public Builder userAgent(String userAgent) { params.userAgent = userAgent; return this; }
            public Builder remoteAddr(String remoteAddr) { params.remoteAddr = remoteAddr; return this; }
            public Builder actor(SecretKeyActorParams actor) { params.actor = actor; return this; }
            public DestroySecretKeyParams build() { return params; }
        }
    }

    public static class SecretKeyUsageParams {
        @JsonProperty("secret_key_id")
        public String secretKeyId;
        @JsonProperty("key_id")
        public String keyId;
        public String id;
        public Integer page;
        public Integer number;
        public Integer size;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final SecretKeyUsageParams params = new SecretKeyUsageParams();
            public Builder secretKeyId(String secretKeyId) { params.secretKeyId = secretKeyId; return this; }
            public Builder keyId(String keyId) { params.keyId = keyId; return this; }
            public Builder id(String id) { params.id = id; return this; }
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
        @JsonProperty("updated_by")
        public String updatedBy;
        @JsonProperty("expires_at")
        public String expiresAt;
        public String status;
        public Boolean active;
        @JsonProperty("revoked_at")
        public String revokedAt;
        @JsonProperty("revoked_by")
        public String revokedBy;
        @JsonProperty("request_id")
        public String requestId;
        @JsonProperty("ip_address")
        public String ipAddress;
        @JsonProperty("user_agent")
        public String userAgent;
        @JsonProperty("key_gen")
        public String keyGen;
        @JsonProperty("generated_by_service")
        public String generatedByService;
        @JsonProperty("generated_by_user_id")
        public String generatedByUserId;
        @JsonProperty("generated_by_team_member_id")
        public String generatedByTeamMemberId;
        @JsonProperty("generated_by_email")
        public String generatedByEmail;
        @JsonProperty("generated_by_name")
        public String generatedByName;
        @JsonProperty("revocation_request_id")
        public String revocationRequestId;
        @JsonProperty("revocation_ip_address")
        public String revocationIpAddress;
        @JsonProperty("revocation_user_agent")
        public String revocationUserAgent;
        @JsonProperty("revoked_by_service")
        public String revokedByService;
        @JsonProperty("revoked_by_user_id")
        public String revokedByUserId;
        @JsonProperty("revoked_by_team_member_id")
        public String revokedByTeamMemberId;
        @JsonProperty("revoked_by_email")
        public String revokedByEmail;
        @JsonProperty("revoked_by_name")
        public String revokedByName;
        @JsonProperty("cipher_text_prefix")
        public String cipherTextPrefix;
        @JsonProperty("cipher_text_length")
        public Integer cipherTextLength;
        @JsonProperty("last_used_at")
        public String lastUsedAt;
        @JsonProperty("usage_count")
        public Integer usageCount;
        @JsonProperty("usage_metrics_available")
        public Boolean usageMetricsAvailable;
    }

    public static class GeneratedSecretKey extends SecretKey {
        public String token;
    }

    public static class SecretKeyPage {
        public Integer number;
        public Integer size;
        public Integer count;
        public Integer total;
        @JsonProperty("has_more")
        public Boolean hasMore;
        @JsonProperty("usage_metrics_available")
        public Boolean usageMetricsAvailable;
        public List<SecretKey> keys;
    }

    public static class SecretKeyUsageRow {
        public String id;
        public String type;
        @JsonProperty("secret_key_id")
        public String secretKeyId;
        @JsonProperty("session_id")
        public String sessionId;
        @JsonProperty("verification_id")
        public String verificationId;
        @JsonProperty("request_id")
        public String requestId;
        @JsonProperty("occurred_at")
        public String occurredAt;
        @JsonProperty("created_at")
        public String createdAt;
        @JsonProperty("initiated_at")
        public String initiatedAt;
        @JsonProperty("expires_at")
        public String expiresAt;
        @JsonProperty("ip_address")
        public String ipAddress;
        @JsonProperty("user_agent")
        public String userAgent;
        public Boolean verified;
        @JsonProperty("auth_result")
        public String authResult;
        @JsonProperty("multi_use")
        public Boolean multiUse;
    }

    public static class SecretKeyUsagePage {
        public Integer number;
        public Integer size;
        public Integer count;
        public Integer total;
        @JsonProperty("has_more")
        public Boolean hasMore;
        @JsonProperty("verification_attempts_available")
        public Boolean verificationAttemptsAvailable;
        public List<SecretKeyUsageRow> rows;
    }

    public static class GenerateSecretKeyResponse {
        public GeneratedSecretKey key;
        public Object error;
    }

    public static class LookupSecretKeyResponse {
        public SecretKey key;
        public Object error;
    }

    public static class PageSecretKeysResponse {
        public SecretKeyPage page;
        public Object error;
    }

    public static class UpdateSecretKeyResponse {
        public SecretKey key;
        public Object error;
    }

    public static class DestroySecretKeyResponse {
        public SecretKey key;
        public Object error;
    }

    public static class SecretKeyUsageResponse {
        public SecretKey key;
        public SecretKeyUsagePage usage;
        public Object error;
    }
}
