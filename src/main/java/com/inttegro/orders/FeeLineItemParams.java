package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.AmountParams;
import java.util.Map;

/** Fee line-item fields supplied in an order request. */
public class FeeLineItemParams {
    public String id;
    public String label;
    public String description;
    @JsonProperty("tax_code") public String taxCode;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public AmountParams amount;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final FeeLineItemParams item = new FeeLineItemParams();
        public Builder id(String id) { item.id = id; return this; }
        public Builder label(String label) { item.label = label; return this; }
        public Builder description(String description) { item.description = description; return this; }
        public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
        public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
        public Builder amount(AmountParams amount) { item.amount = amount; return this; }
        public FeeLineItemParams build() { return item; }
    }
}
