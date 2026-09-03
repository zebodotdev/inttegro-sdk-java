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

public class OrderPayParams {
    @JsonProperty("order_id") public String orderId;
    @JsonProperty("request_meta") public RequestMeta requestMeta;
    @JsonProperty("payment_method_id") public String paymentMethodId;
    @JsonProperty("payment_method_data") public com.inttegro.paymentmethods.PaymentMethodData paymentMethodData;
    @JsonProperty("paid_out_of_band") public Boolean paidOutOfBand;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayParams params = new OrderPayParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public Builder paymentMethodData(com.inttegro.paymentmethods.PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
        public Builder paidOutOfBand(Boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
        public Builder paidOutOfBand(boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
        public OrderPayParams build() { return params; }
    }
}
