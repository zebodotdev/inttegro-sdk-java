package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RefundLineItem {
    public String id;
    @JsonProperty("order_line_item_id") public String orderLineItemId;
    @JsonProperty("original_amount_paid") public Amount originalAmountPaid;
    @JsonProperty("refund_amount") public Amount refundAmount;
    public RefundReason reason;
    @JsonProperty("reason_details") public String reasonDetails;
}
