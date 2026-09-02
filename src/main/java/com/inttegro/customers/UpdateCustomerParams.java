package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class UpdateCustomerParams {
    @JsonProperty("billing_address")
    public Address billingAddress;
    @JsonProperty("custom_data")
    public Map<String, Object> customData;
    @JsonProperty("customer_id")
    public String customerId;
    @JsonProperty("email_address")
    public String emailAddress;
    public String name;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String reference;
    @JsonProperty("shipping_address")
    public Address shippingAddress;
    public String suffix;
    public String title;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UpdateCustomerParams params = new UpdateCustomerParams();
        public Builder billingAddress(Address billingAddress) { params.billingAddress = billingAddress; return this; }
        public Builder customData(Map<String, Object> customData) { params.customData = customData; return this; }
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public Builder emailAddress(String emailAddress) { params.emailAddress = emailAddress; return this; }
        public Builder name(String name) { params.name = name; return this; }
        public Builder phoneNumber(String phoneNumber) { params.phoneNumber = phoneNumber; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder shippingAddress(Address shippingAddress) { params.shippingAddress = shippingAddress; return this; }
        public Builder suffix(String suffix) { params.suffix = suffix; return this; }
        public Builder title(String title) { params.title = title; return this; }
        public UpdateCustomerParams build() { return params; }
    }
}
