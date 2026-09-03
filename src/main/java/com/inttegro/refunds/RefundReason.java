package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
