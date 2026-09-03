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

public class FeeLineItem {
    public String id;
    public String label;
    public String description;
    @JsonProperty("tax_code")
    public String taxCode;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    public Money amount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FeeLineItem item = new FeeLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder label(String label) { item.label = label; return this; }
        public Builder description(String description) { item.description = description; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
        public Builder amount(Money amount) { item.amount = amount; return this; }
        public FeeLineItem build() { return item; }
    }
}
