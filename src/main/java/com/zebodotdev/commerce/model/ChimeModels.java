package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.ChimeRecipientType;
import com.zebodotdev.commerce.model.CommonModels.ChimeTransport;

import java.util.List;
import java.util.Map;

public class ChimeModels {
    public static class Phone { public String number; }
    public static class Email { public String address; }

    public static class ChimeRecipient {
        public ChimeRecipientType type;
        public String name;
        public Phone phone;
        public Email email;
    }

    public static class SendChimeParams {
        public ChimeRecipient recipient;
        @JsonProperty("full_message") public String fullMessage;
        public ChimeTransport transport;
        public String sender;
        public String purpose;
        @JsonProperty("custom_data") public Map<String, String> customData;
        @JsonProperty("idempotency_key") public String idempotencyKey;
    }

    public static class ScheduleChimeParams {
        public List<String> recipients;
        @JsonProperty("full_message") public String fullMessage;
        @JsonProperty("send_after") public String sendAfter;
        @JsonProperty("sender_id") public String senderId;
        public String purpose;
        @JsonProperty("idempotency_key") public String idempotencyKey;
    }

    public static class LookupChimeParams { @JsonProperty("chime_id") public String chimeId; }

    public static class Chime {
        public String id;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("full_message") public String fullMessage;
        public ChimeRecipient recipient;
        @JsonProperty("sender_id") public String senderId;
        public String purpose;
        @JsonProperty("custom_data") public Map<String, String> customData;
        public Object delivery;
        public Object transmission;
    }

    public static class ChimeResponse { public Chime chime; }
}
