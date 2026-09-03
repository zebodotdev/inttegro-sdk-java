package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

public class OrderPayoutSettings {
    public OrderPayoutDestination destination;
    @JsonProperty("enable_fx")
    public Boolean enableFX;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayoutSettings settings = new OrderPayoutSettings();
        public Builder destination(OrderPayoutDestination destination) { settings.destination = destination; return this; }
        public Builder enableFX(Boolean enableFX) { settings.enableFX = enableFX; return this; }
        public Builder enableFX(boolean enableFX) { settings.enableFX = enableFX; return this; }
        public OrderPayoutSettings build() { return settings; }
    }
}
