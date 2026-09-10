package com.inttegro.paymentmethods;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class VerificationMetadata {
    @JsonProperty("completed_at")
    public OffsetDateTime completedAt;
    @JsonProperty("initiated_at")
    public OffsetDateTime initiatedAt;
    public String mechanism;
    @JsonProperty("request_id")
    public String requestId;
    public String type;
}
