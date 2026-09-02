package com.inttegro.broadcasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BroadcastResponse {
    @JsonProperty("broadcast_id") public String broadcastId;
    public String status;
    @JsonProperty("recipients_count") public Integer recipientsCount;
    @JsonProperty("queued_at") public String queuedAt;
}
