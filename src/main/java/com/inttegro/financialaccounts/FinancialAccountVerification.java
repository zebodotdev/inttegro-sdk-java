package com.inttegro.financialaccounts;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Current financial-account verification state. */
public final class FinancialAccountVerification {
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("completed_at") public OffsetDateTime completedAt;
    public FinancialAccountVerificationRequest request;
}
