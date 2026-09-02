package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Shipping {
    public Address address;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Shipping shipping = new Shipping();
        public Builder address(Address address) { shipping.address = address; return this; }
        public Shipping build() { return shipping; }
    }
}
