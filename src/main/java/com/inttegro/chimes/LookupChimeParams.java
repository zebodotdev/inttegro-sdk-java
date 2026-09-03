package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class LookupChimeParams {
    @JsonProperty("chime_id") public String chimeId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupChimeParams params = new LookupChimeParams();
        public Builder chimeId(String chimeId) { params.chimeId = chimeId; return this; }
        public LookupChimeParams build() { return params; }
    }
}
