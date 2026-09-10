package com.inttegro.customers;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    public CustomerBalance balance;
    @JsonProperty("billing_address")
    public Address billingAddress;
    public String id;
    public String name;
    public String title;
    public String suffix;
    public String reference;
    @JsonProperty("email_address")
    public String emailAddress;
    @JsonProperty("phone_number")
    public String phoneNumber;
    @JsonProperty("custom_data")
    public CustomData customData;
    @JsonProperty("created_at")
    public OffsetDateTime createdAt;
    public Boolean guest;
    @JsonProperty("shipping_address")
    public Address shippingAddress;
    @JsonProperty("updated_at")
    public OffsetDateTime updatedAt;
}
