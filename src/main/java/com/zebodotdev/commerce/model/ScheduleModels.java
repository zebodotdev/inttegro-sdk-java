package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScheduleModels {
    public static class LookupScheduleParams { @JsonProperty("schedule_id") public String scheduleId; }
    public static class CancelScheduleParams { @JsonProperty("schedule_id") public String scheduleId; }

    public static class ScheduleError {
        public String recipient;
        @JsonProperty("fix_code") public String fixCode;
        public String type;
    }

    public static class ScheduleDetail {
        public String id;
        public List<String> recipients;
        public String content;
        @JsonProperty("sender_id") public String senderId;
        public String purpose;
        @JsonProperty("send_after") public String sendAfter;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("executed_at") public String executedAt;
        @JsonProperty("canceled_at") public String canceledAt;
        public List<ScheduleError> errors;
        @JsonProperty("chime_ids") public List<String> chimeIds;
    }

    public static class ScheduledChime {
        public String id;
        public List<String> recipients;
        @JsonProperty("full_message") public String fullMessage;
        @JsonProperty("sender_id") public String senderId;
        public String purpose;
        @JsonProperty("send_after") public String sendAfter;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("executed_at") public String executedAt;
    }

    public static class ScheduleResponse {
        @JsonProperty("scheduled_chime") public ScheduledChime scheduledChime;
    }

    public static class ScheduleLookupResponse {
        @JsonProperty("scheduled_chime") public ScheduleDetail scheduledChime;
    }

    public static class ScheduleCancelResponse {
        @JsonProperty("scheduled_chime") public ScheduleDetail scheduledChime;
    }
}
