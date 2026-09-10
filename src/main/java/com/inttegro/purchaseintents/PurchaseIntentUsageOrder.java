package com.inttegro.purchaseintents;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseIntentUsageOrder {
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    public String id;
}
