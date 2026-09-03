package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduledChime {
    public String id;
    public List<String> recipients;
    @JsonProperty("full_message") public String fullMessage;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("send_after") public String sendAfter;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("executed_at") public String executedAt;
}
