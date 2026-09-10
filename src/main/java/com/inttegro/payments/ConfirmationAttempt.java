package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmationAttempt {
    public String status;
    public boolean confirmed;
    public String reason;
    @JsonProperty("executed_at") public OffsetDateTime executedAt;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
}
