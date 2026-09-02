package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PullPushConfig {
    public Boolean enabled;
    @JsonProperty("enabled_at") public String enabledAt;
    public Map<String, Object> mandate;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PullPushConfig config = new PullPushConfig();
        public Builder enabled(Boolean enabled) { config.enabled = enabled; return this; }
        public Builder enabled(boolean enabled) { config.enabled = enabled; return this; }
        public Builder enabledAt(String enabledAt) { config.enabledAt = enabledAt; return this; }
        public Builder mandate(Map<String, Object> mandate) { config.mandate = mandate; return this; }
        public PullPushConfig build() { return config; }
    }
}
