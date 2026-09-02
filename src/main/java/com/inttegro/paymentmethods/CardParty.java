package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class CardParty {
    @JsonProperty("email_address")
    public String emailAddress;
    public String name;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String type;
}
