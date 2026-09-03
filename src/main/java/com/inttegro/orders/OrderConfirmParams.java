package com.inttegro.orders;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.LineItemType;
import com.inttegro.common.PaymentMethodType;
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

public class OrderConfirmParams {
    @JsonProperty("order_id") public String orderId;
    @JsonProperty("request_meta") public RequestMeta requestMeta;
    public String token;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderConfirmParams params = new OrderConfirmParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder token(String token) { params.token = token; return this; }
        public OrderConfirmParams build() { return params; }
    }
}
