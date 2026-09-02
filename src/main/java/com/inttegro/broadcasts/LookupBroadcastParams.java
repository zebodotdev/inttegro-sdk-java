package com.inttegro.broadcasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LookupBroadcastParams {
    @JsonProperty("broadcast_id") public String broadcastId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupBroadcastParams params = new LookupBroadcastParams();
        public Builder broadcastId(String broadcastId) { params.broadcastId = broadcastId; return this; }
        public LookupBroadcastParams build() { return params; }
    }
}
