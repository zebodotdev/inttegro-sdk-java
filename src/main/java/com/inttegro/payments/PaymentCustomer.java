package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentCustomer {
    public String id;
    @JsonProperty("email_address") public String emailAddress;
    public boolean guest;
    public String name;
    @JsonProperty("phone_number") public String phoneNumber;
    @JsonProperty("billing_address") public PaymentAddress billingAddress;
    @JsonProperty("shipping_address") public PaymentAddress shippingAddress;
}
