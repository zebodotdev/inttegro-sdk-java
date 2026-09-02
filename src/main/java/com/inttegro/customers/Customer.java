package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Customer {
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
    public Map<String, String> customData;
    @JsonProperty("created_at")
    public String createdAt;
}
