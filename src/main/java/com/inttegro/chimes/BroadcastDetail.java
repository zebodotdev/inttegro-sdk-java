package com.inttegro.chimes;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BroadcastDetail {
    public String id;
    public List<String> recipients;
    public String content;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("send_after") public OffsetDateTime sendAfter;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("executed_at") public OffsetDateTime executedAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    public List<BroadcastError> errors;
    @JsonProperty("chime_ids") public List<String> chimeIds;
}
