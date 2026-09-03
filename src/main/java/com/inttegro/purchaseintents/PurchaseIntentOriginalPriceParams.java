package com.inttegro.purchaseintents;

import com.inttegro.prices.PriceParams;

/** Optional comparison price supplied when creating a purchase intent. */
public class PurchaseIntentOriginalPriceParams {
    public String id;
    public PriceParams nominal;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PurchaseIntentOriginalPriceParams price = new PurchaseIntentOriginalPriceParams();
        public Builder id(String id) { price.id = id; return this; }
        public Builder nominal(PriceParams nominal) { price.nominal = nominal; return this; }
        public PurchaseIntentOriginalPriceParams build() { return price; }
    }
}
