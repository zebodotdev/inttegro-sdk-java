package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Address {
    public String name;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String line1;
    public String line2;
    public String city;
    public String town;
    public String region;
    public String district;
    public String country;
    @JsonProperty("post_code")
    public String postCode;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Address address = new Address();
        public Builder name(String name) { address.name = name; return this; }
        public Builder phoneNumber(String phone) { address.phoneNumber = phone; return this; }
        public Builder line1(String line1) { address.line1 = line1; return this; }
        public Builder line2(String line2) { address.line2 = line2; return this; }
        public Builder city(String city) { address.city = city; return this; }
        public Builder town(String town) { address.town = town; return this; }
        public Builder region(String region) { address.region = region; return this; }
        public Builder district(String district) { address.district = district; return this; }
        public Builder country(String country) { address.country = country; return this; }
        public Builder postCode(String postCode) { address.postCode = postCode; return this; }
        public Address build() { return address; }
    }
}
