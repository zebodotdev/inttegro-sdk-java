package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;

public class FeeLineItem {
    public String id;
    public String label;
    public String description;
    @JsonProperty("tax_code")
    public String taxCode;
    public Amount amount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FeeLineItem item = new FeeLineItem();
        public Builder id(String id) { item.id = id; return this; }
        public Builder label(String label) { item.label = label; return this; }
        public Builder description(String description) { item.description = description; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder amount(Amount amount) { item.amount = amount; return this; }
        public FeeLineItem build() { return item; }
    }
}
