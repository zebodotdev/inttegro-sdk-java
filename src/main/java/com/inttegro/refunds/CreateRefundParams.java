package com.inttegro.refunds;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateRefundParams {
    @JsonProperty("line_items") public List<CreateRefundLineItem> lineItems;
    @JsonProperty("order_id") public String orderId;
    public RefundReason reason;
    @JsonProperty("reason_details") public String reasonDetails;
    public String reference;
    @JsonProperty("custom_data") public Map<String, String> customData;
    @JsonProperty("request_meta") public RequestMeta requestMeta;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateRefundParams params = new CreateRefundParams();
        private final List<CreateRefundLineItem> lineItems = new ArrayList<>();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder lineItem(CreateRefundLineItem lineItem) { lineItems.add(lineItem); return this; }
        public Builder lineItems(List<CreateRefundLineItem> lineItems) { this.lineItems.addAll(lineItems); return this; }
        public Builder reason(RefundReason reason) { params.reason = reason; return this; }
        public Builder reasonDetails(String reasonDetails) { params.reasonDetails = reasonDetails; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public CreateRefundParams build() { params.lineItems = lineItems; return params; }
    }
}
