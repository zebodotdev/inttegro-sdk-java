package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LookupPurchaseIntentParams {
    public String id;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupPurchaseIntentParams params = new LookupPurchaseIntentParams();
        public Builder id(String id) { params.id = id; return this; }
        public LookupPurchaseIntentParams build() { return params; }
    }
}
