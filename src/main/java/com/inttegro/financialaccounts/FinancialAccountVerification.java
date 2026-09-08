package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Current financial-account verification state. */
public final class FinancialAccountVerification {
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("completed_at") public String completedAt;
    public FinancialAccountVerificationRequest request;
}
