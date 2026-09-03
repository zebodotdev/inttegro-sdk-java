package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduleDetail {
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
