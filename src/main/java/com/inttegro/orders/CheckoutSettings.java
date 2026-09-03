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

public class CheckoutSettings {
    @JsonProperty("redirect_url")
    public String redirectUrl;
    @JsonProperty("cancel_url")
    public String cancelUrl;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CheckoutSettings settings = new CheckoutSettings();
        public Builder redirectUrl(String redirectUrl) { settings.redirectUrl = redirectUrl; return this; }
        public Builder cancelUrl(String cancelUrl) { settings.cancelUrl = cancelUrl; return this; }
        public CheckoutSettings build() { return settings; }
    }
}
