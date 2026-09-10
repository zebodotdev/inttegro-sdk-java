package com.inttegro.payouts;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PayoutError {
    public String cause;
    public String message;
    @JsonProperty("occurred_at") public OffsetDateTime occurredAt;
    public String type;
}
