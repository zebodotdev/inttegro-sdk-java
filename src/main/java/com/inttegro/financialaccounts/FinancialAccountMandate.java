package com.inttegro.financialaccounts;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Mandate authorizing pulls from a financial account. */
public final class FinancialAccountMandate {
    public String id;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("user_agent") public String userAgent;
    @JsonProperty("ip_address") public String ipAddress;
}
