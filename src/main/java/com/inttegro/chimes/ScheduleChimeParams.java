package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class ScheduleChimeParams {
    public List<String> recipients;
    @JsonProperty("full_message") public String fullMessage;
    @JsonProperty("send_after") public String sendAfter;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("idempotency_key") public String idempotencyKey;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ScheduleChimeParams params = new ScheduleChimeParams();
        public Builder recipients(List<String> recipients) { params.recipients = recipients; return this; }
        public Builder fullMessage(String fullMessage) { params.fullMessage = fullMessage; return this; }
        public Builder sendAfter(String sendAfter) { params.sendAfter = sendAfter; return this; }
        public Builder senderId(String senderId) { params.senderId = senderId; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder idempotencyKey(String idempotencyKey) { params.idempotencyKey = idempotencyKey; return this; }
        public ScheduleChimeParams build() { return params; }
    }
}
