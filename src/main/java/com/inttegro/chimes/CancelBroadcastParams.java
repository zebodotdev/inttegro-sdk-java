package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CancelBroadcastParams {
    @JsonProperty("broadcast_id") public String broadcastId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CancelBroadcastParams params = new CancelBroadcastParams();
        public Builder broadcastId(String broadcastId) { params.broadcastId = broadcastId; return this; }
        public CancelBroadcastParams build() { return params; }
    }
}
