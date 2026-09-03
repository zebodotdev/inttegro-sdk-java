package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PaymentMethodOwnerAddress {
    public String city;
    public String country;
    @JsonProperty("line1")
    public String line1;
    @JsonProperty("line2")
    public String line2;
    public String name;
    @JsonProperty("phone_number")
    public String phoneNumber;
    @JsonProperty("post_code")
    public String postCode;
    public String region;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PaymentMethodOwnerAddress address = new PaymentMethodOwnerAddress();
        public Builder city(String city) { address.city = city; return this; }
        public Builder country(String country) { address.country = country; return this; }
        public Builder line1(String line1) { address.line1 = line1; return this; }
        public Builder line2(String line2) { address.line2 = line2; return this; }
        public Builder name(String name) { address.name = name; return this; }
        public Builder phoneNumber(String phoneNumber) { address.phoneNumber = phoneNumber; return this; }
        public Builder postCode(String postCode) { address.postCode = postCode; return this; }
        public Builder region(String region) { address.region = region; return this; }
        public PaymentMethodOwnerAddress build() { return address; }
    }
}
