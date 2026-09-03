package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.AmountParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateRefundLineItem {
    @JsonProperty("order_line_item_id") public String orderLineItemId;
    @JsonProperty("refund_amount") public AmountParams refundAmount;
    public RefundReason reason;
    @JsonProperty("reason_details") public String reasonDetails;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateRefundLineItem item = new CreateRefundLineItem();
        public Builder orderLineItemId(String orderLineItemId) { item.orderLineItemId = orderLineItemId; return this; }
        public Builder refundAmount(AmountParams refundAmount) { item.refundAmount = refundAmount; return this; }
        public Builder reason(RefundReason reason) { item.reason = reason; return this; }
        public Builder reasonDetails(String reasonDetails) { item.reasonDetails = reasonDetails; return this; }
        public CreateRefundLineItem build() { return item; }
    }
}
