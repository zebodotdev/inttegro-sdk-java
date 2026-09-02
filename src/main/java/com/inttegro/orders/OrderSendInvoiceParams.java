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
import com.inttegro.paymentmethods.PaymentMethodObject;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OrderSendInvoiceParams {
    @JsonProperty("order_id") public String orderId;
    @JsonProperty("request_meta") public RequestMeta requestMeta;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final OrderSendInvoiceParams params = new OrderSendInvoiceParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
        public OrderSendInvoiceParams build() { return params; }
    }
}
