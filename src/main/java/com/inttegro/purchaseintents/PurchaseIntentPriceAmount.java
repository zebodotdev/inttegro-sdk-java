package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentPriceAmount {
    public String currency;
    public Long value;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentPriceAmount amount = new PurchaseIntentPriceAmount();
        public Builder currency(String currency) { amount.currency = currency; return this; }
        public Builder value(Long value) { amount.value = value; return this; }
        public PurchaseIntentPriceAmount build() { return amount; }
    }
}
