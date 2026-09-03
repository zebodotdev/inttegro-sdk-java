package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class VerificationMetadata {
    @JsonProperty("completed_at")
    public String completedAt;
    @JsonProperty("initiated_at")
    public String initiatedAt;
    public String mechanism;
    @JsonProperty("request_id")
    public String requestId;
    public String type;
}
