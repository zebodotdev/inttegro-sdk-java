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
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ShippingLineItem {
    public String id;
    public Money fee;
    @JsonProperty("tax_code")
    public String taxCode;
    @JsonProperty("custom_data")
    public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ShippingLineItem item = new ShippingLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder fee(Money fee) { item.fee = fee; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
        public ShippingLineItem build() { return item; }
    }
}
