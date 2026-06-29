package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BroadcastModels {
    public static class BroadcastChimeParams {
        public List<String> recipients;
        @JsonProperty("message_template") public String messageTemplate;
        @JsonProperty("service_name") public String serviceName;
        public String sender;
        public String purpose;
        @JsonProperty("preferred_gateway") public String preferredGateway;
        @JsonProperty("idempotency_key") public String idempotencyKey;
    }

    public static class BroadcastResponse {
        @JsonProperty("broadcast_id") public String broadcastId;
        public String status;
        @JsonProperty("recipients_count") public Integer recipientsCount;
        @JsonProperty("queued_at") public String queuedAt;
    }

    public static class LookupBroadcastParams { @JsonProperty("broadcast_id") public String broadcastId; }
    public static class CancelBroadcastParams { @JsonProperty("broadcast_id") public String broadcastId; }

    public static class BroadcastError {
        public String recipient;
        @JsonProperty("fix_code") public String fixCode;
        public String type;
    }

    public static class BroadcastDetail {
        public String id;
        public List<String> recipients;
        public String content;
        @JsonProperty("sender_id") public String senderId;
        public String purpose;
        @JsonProperty("send_after") public String sendAfter;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("executed_at") public String executedAt;
        @JsonProperty("canceled_at") public String canceledAt;
        public List<BroadcastError> errors;
        @JsonProperty("chime_ids") public List<String> chimeIds;
    }

    public static class LookupBroadcastResponse { public BroadcastDetail broadcast; }
    public static class BroadcastCancelResponse { public BroadcastDetail broadcast; }
}
