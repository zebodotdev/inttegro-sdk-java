package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;

public class ShippingLineItem {
    public String id;
    public Amount fee;
    public String label;
    @JsonProperty("tax_code")
    public String taxCode;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ShippingLineItem item = new ShippingLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder fee(Amount fee) { item.fee = fee; return this; }
        public Builder label(String label) { item.label = label; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public ShippingLineItem build() { return item; }
    }
}
