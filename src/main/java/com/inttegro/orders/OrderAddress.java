package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderAddress {
    public String name;
    @JsonProperty("phone_number") public String phoneNumber;
    public String line1;
    public String line2;
    public String city;
    public String region;
    @JsonProperty("post_code") public String postCode;
    public String country;
}
