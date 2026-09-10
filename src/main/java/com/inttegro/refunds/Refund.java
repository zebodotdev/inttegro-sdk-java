package com.inttegro.refunds;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Refund {
    public String id;
    @JsonProperty("order_id") public String orderId;
    public RefundStatus status;
    public Amount total;
    @JsonProperty("line_items") public List<RefundLineItem> lineItems;
    public RefundReason reason;
    @JsonProperty("reason_details") public String reasonDetails;
    public String reference;
    @JsonProperty("custom_data") public CustomData customData;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("processing_at") public OffsetDateTime processingAt;
    @JsonProperty("succeeded_at") public OffsetDateTime succeededAt;
    @JsonProperty("failed_at") public OffsetDateTime failedAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
}
