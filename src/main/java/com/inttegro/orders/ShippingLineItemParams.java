package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.AmountParams;
import java.util.Map;

/** Shipping line-item fields supplied in an order request. */
public class ShippingLineItemParams {
    public String id;
    public AmountParams fee;
    @JsonProperty("tax_code") public String taxCode;
    @JsonProperty("custom_data") public Map<String, String> customData;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final ShippingLineItemParams item = new ShippingLineItemParams();
        public Builder id(String id) { item.id = id; return this; }
        public Builder fee(AmountParams fee) { item.fee = fee; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
        public ShippingLineItemParams build() { return item; }
    }
}
