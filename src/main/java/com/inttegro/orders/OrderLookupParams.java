package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import com.inttegro.customers.Address;
import com.inttegro.customers.BillingDetails;
import com.inttegro.customers.CustomerData;
import com.inttegro.customers.Shipping;
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OrderLookupParams {
    @JsonProperty("order_id") public String orderId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderLookupParams params = new OrderLookupParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public OrderLookupParams build() { return params; }
    }
}
