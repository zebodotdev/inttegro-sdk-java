package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class CustomerData {
    public String name;
    public String title;
    public String suffix;
    @JsonProperty("email_address")
    public String emailAddress;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String reference;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    @JsonProperty("created_at")
    public String createdAt;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CustomerData data = new CustomerData();
        public Builder name(String name) { data.name = name; return this; }
        public Builder title(String title) { data.title = title; return this; }
        public Builder suffix(String suffix) { data.suffix = suffix; return this; }
        public Builder email(String email) { data.emailAddress = email; return this; }
        public Builder phoneNumber(String phone) { data.phoneNumber = phone; return this; }
        public Builder reference(String ref) { data.reference = ref; return this; }
        public Builder customData(Map<String, String> data) { this.data.customData = data; return this; }
        public CustomerData build() { return data; }
    }
}
