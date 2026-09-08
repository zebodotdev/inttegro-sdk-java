package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.CustomData;

/** Order-level invoice rendering settings. */
public final class InvoiceSettings {
    public String number;
    public String memo;
    public String footer;
    @JsonProperty("custom_data") public CustomData customData;

    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private final InvoiceSettings settings = new InvoiceSettings();
        public Builder number(String number) { settings.number = number; return this; }
        public Builder memo(String memo) { settings.memo = memo; return this; }
        public Builder footer(String footer) { settings.footer = footer; return this; }
        public Builder customData(CustomData customData) { settings.customData = customData; return this; }
        public InvoiceSettings build() { return settings; }
    }
}
