package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCustomer {
    public String id;
    @JsonProperty("email_address") public String emailAddress;
    public boolean guest;
    public String name;
    @JsonProperty("phone_number") public String phoneNumber;
    @JsonProperty("billing_address") public OrderAddress billingAddress;
    @JsonProperty("shipping_address") public OrderAddress shippingAddress;
}
