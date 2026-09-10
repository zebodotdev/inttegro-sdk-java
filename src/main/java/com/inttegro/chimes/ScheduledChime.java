package com.inttegro.chimes;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduledChime {
    public String id;
    public List<String> recipients;
    @JsonProperty("full_message") public String fullMessage;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("send_after") public OffsetDateTime sendAfter;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("executed_at") public OffsetDateTime executedAt;
}
