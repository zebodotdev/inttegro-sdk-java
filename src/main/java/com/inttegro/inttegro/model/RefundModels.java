package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.inttegro.model.CommonModels.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundModels {
    public enum RefundReason {
        @JsonProperty("requested_by_customer") REQUESTED_BY_CUSTOMER,
        @JsonProperty("duplicate") DUPLICATE,
        @JsonProperty("fraudulent") FRAUDULENT,
        @JsonProperty("order_canceled") ORDER_CANCELED,
        @JsonProperty("item_returned") ITEM_RETURNED,
        @JsonProperty("item_damaged") ITEM_DAMAGED,
        @JsonProperty("item_not_received") ITEM_NOT_RECEIVED,
        @JsonProperty("item_not_as_described") ITEM_NOT_AS_DESCRIBED,
        @JsonProperty("custom") CUSTOM
    }

    public enum RefundStatus {
        @JsonProperty("canceled") CANCELED,
        @JsonProperty("failed") FAILED,
        @JsonProperty("pending") PENDING,
        @JsonProperty("processing") PROCESSING,
        @JsonProperty("succeeded") SUCCEEDED
    }

    public static class CreateRefundLineItem {
        @JsonProperty("order_line_item_id") public String orderLineItemId;
        @JsonProperty("refund_amount") public Money refundAmount;
        public RefundReason reason;
        @JsonProperty("reason_details") public String reasonDetails;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CreateRefundLineItem item = new CreateRefundLineItem();
            public Builder orderLineItemId(String orderLineItemId) { item.orderLineItemId = orderLineItemId; return this; }
            public Builder refundAmount(Money refundAmount) { item.refundAmount = refundAmount; return this; }
            public Builder reason(RefundReason reason) { item.reason = reason; return this; }
            public Builder reasonDetails(String reasonDetails) { item.reasonDetails = reasonDetails; return this; }
            public CreateRefundLineItem build() { return item; }
        }
    }

    public static class CreateRefundParams {
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

    public static class CancelRefundParams {
        @JsonProperty("refund_id") public String refundId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CancelRefundParams params = new CancelRefundParams();
            public Builder refundId(String refundId) { params.refundId = refundId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public CancelRefundParams build() { return params; }
        }
    }

    public static class LookupRefundParams {
        @JsonProperty("refund_id") public String refundId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupRefundParams params = new LookupRefundParams();
            public Builder refundId(String refundId) { params.refundId = refundId; return this; }
            public LookupRefundParams build() { return params; }
        }
    }

    public static class RefundPageParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final RefundPageParams params = new RefundPageParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public RefundPageParams build() { return params; }
        }
    }

    public static class RefundLineItem {
        public String id;
        @JsonProperty("order_line_item_id") public String orderLineItemId;
        @JsonProperty("original_amount_paid") public Money originalAmountPaid;
        @JsonProperty("refund_amount") public Money refundAmount;
        public RefundReason reason;
        @JsonProperty("reason_details") public String reasonDetails;
    }

    public static class Refund {
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

    public static class RefundResponse { public Refund refund; }

    public static class RefundPage {
        public Integer number;
        public List<Refund> refunds;
        public Integer size;
    }

    public static class RefundPageResponse { public RefundPage page; }
}
