package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import java.util.List;
import java.util.Map;

public class Payout {
    public String id;
    @JsonProperty("application_id") public String applicationId;
    @JsonProperty("destination_id") public String destinationId;
    public Amount amount;
    @JsonProperty("max_amount") public Amount maxAmount;
    public PayoutStatus status;
    @JsonProperty("initiated_by") public String initiatedBy;
    @JsonProperty("execute_after") public String executeAfter;
    @JsonProperty("scheduled_at") public String scheduledAt;
    @JsonProperty("canceled_at") public String canceledAt;
    @JsonProperty("latest_attempt_id") public String latestAttemptId;
    @JsonProperty("latest_error") public Object latestError;
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("executed_at") public String executedAt;
    @JsonProperty("expected_at") public String expectedAt;
    @JsonProperty("succeeded_at") public String succeededAt;
    @JsonProperty("balance_transaction_ids") public List<String> balanceTransactionIds;
}
