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

    public static class PageChimesParams {
        @JsonProperty("customer_id") public String customerId;
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;
        public String recipient;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PageChimesParams params = new PageChimesParams();
            public Builder customerId(String customerId) { params.customerId = customerId; return this; }
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public Builder recipient(String recipient) { params.recipient = recipient; return this; }
            public PageChimesParams build() { return params; }
        }
    }

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
    public static class ChimePage {
        public Integer number;
        public Integer size;
        public List<Chime> chimes;
    }
    public static class PageChimesResponse { public ChimePage page; }
}
