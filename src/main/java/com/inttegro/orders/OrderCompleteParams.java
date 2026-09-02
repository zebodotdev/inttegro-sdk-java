package com.inttegro.orders;

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

public class OrderCompleteParams {
    @JsonProperty("order_id") public String orderId;
    @JsonProperty("paid_out_of_band") public Boolean paidOutOfBand;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderCompleteParams params = new OrderCompleteParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder paidOutOfBand(Boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
        public Builder paidOutOfBand(boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
        public OrderCompleteParams build() { return params; }
    }
}
