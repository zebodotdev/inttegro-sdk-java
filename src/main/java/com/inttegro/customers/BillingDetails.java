package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class BillingDetails {
    public String name;
    @JsonProperty("email_address")
    public String emailAddress;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public Address address;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final BillingDetails details = new BillingDetails();
        public Builder name(String name) { details.name = name; return this; }
        public Builder email(String email) { details.emailAddress = email; return this; }
        public Builder phoneNumber(String phone) { details.phoneNumber = phone; return this; }
        public Builder address(Address addr) { details.address = addr; return this; }
        public BillingDetails build() { return details; }
    }
}
