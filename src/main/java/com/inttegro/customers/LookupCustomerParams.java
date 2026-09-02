package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class LookupCustomerParams {
    @JsonProperty("customer_id")
    public String customerId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupCustomerParams params = new LookupCustomerParams();
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public LookupCustomerParams build() { return params; }
    }
}
