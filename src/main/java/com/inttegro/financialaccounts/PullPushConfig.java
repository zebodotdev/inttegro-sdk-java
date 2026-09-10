package com.inttegro.financialaccounts;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PullPushConfig {
    public Boolean enabled;
    @JsonProperty("enabled_at") public OffsetDateTime enabledAt;
    public FinancialAccountMandate mandate;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PullPushConfig config = new PullPushConfig();
        public Builder enabled(Boolean enabled) { config.enabled = enabled; return this; }
        public Builder enabled(boolean enabled) { config.enabled = enabled; return this; }
        public Builder enabledAt(OffsetDateTime enabledAt) { config.enabledAt = enabledAt; return this; }
        public Builder mandate(FinancialAccountMandate mandate) { config.mandate = mandate; return this; }
        public PullPushConfig build() { return config; }
    }
}
