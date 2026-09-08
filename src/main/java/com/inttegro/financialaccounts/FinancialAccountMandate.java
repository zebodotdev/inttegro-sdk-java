package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Mandate authorizing pulls from a financial account. */
public final class FinancialAccountMandate {
    public String id;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("user_agent") public String userAgent;
    @JsonProperty("ip_address") public String ipAddress;
}
