package com.inttegro.paymentmethods;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodSupplied {
    @JsonProperty("attempt_id") public String attemptId;
    public String by;
    public String channel;
    @JsonProperty("resource_id") public String resourceId;
    @JsonProperty("resource_type") public String resourceType;
    @JsonProperty("supplied_at") public OffsetDateTime suppliedAt;
}
