package com.inttegro.customers;

import com.inttegro.CustomDataInput;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCustomerParams {
    @JsonProperty("request_meta")
    public RequestMeta requestMeta;
    public String name;
    public String title;
    public String suffix;
    public String reference;
    @JsonProperty("email_address")
    public String emailAddress;
    @JsonProperty("phone_number")
    public String phoneNumber;
    @JsonProperty("custom_data")
    public CustomDataInput customData;
    @JsonProperty("billing_address")
    public Address billingAddress;
    @JsonProperty("shipping_address")
    public Address shippingAddress;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateCustomerParams params = new CreateCustomerParams();
        public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
        public Builder name(String name) { params.name = name; return this; }
        public Builder title(String title) { params.title = title; return this; }
        public Builder suffix(String suffix) { params.suffix = suffix; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public Builder emailAddress(String emailAddress) {
            params.emailAddress = emailAddress;
            return this;
        }
        public Builder phoneNumber(String phoneNumber) {
            params.phoneNumber = phoneNumber;
            return this;
        }
        public Builder customData(CustomDataInput customData) {
            params.customData = customData;
            return this;
        }
        public Builder billingAddress(Address billingAddress) { params.billingAddress = billingAddress; return this; }
        public Builder shippingAddress(Address shippingAddress) { params.shippingAddress = shippingAddress; return this; }
        public CreateCustomerParams build() { return params; }
    }
}
