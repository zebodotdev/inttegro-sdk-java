package com.inttegro.payouts;

import java.time.OffsetDateTime;
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
    @JsonProperty("execute_after") public OffsetDateTime executeAfter;
    @JsonProperty("scheduled_at") public OffsetDateTime scheduledAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    @JsonProperty("latest_attempt_id") public String latestAttemptId;
    @JsonProperty("latest_error") public PayoutError latestError;
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("executed_at") public OffsetDateTime executedAt;
    @JsonProperty("expected_at") public OffsetDateTime expectedAt;
    @JsonProperty("succeeded_at") public OffsetDateTime succeededAt;
    @JsonProperty("balance_transaction_ids") public List<String> balanceTransactionIds;
}
