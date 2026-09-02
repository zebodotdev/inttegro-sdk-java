package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Refund {
    public String id;
    @JsonProperty("order_id") public String orderId;
    public RefundStatus status;
    public Money total;
    @JsonProperty("line_items") public List<RefundLineItem> lineItems;
    public RefundReason reason;
    @JsonProperty("reason_details") public String reasonDetails;
    public String reference;
    @JsonProperty("custom_data") public Map<String, String> customData;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("processing_at") public String processingAt;
    @JsonProperty("succeeded_at") public String succeededAt;
    @JsonProperty("failed_at") public String failedAt;
    @JsonProperty("canceled_at") public String canceledAt;
}
