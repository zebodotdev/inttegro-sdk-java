package com.inttegro.bankaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccountOwnerAddress {
    public String id;
    @JsonProperty("application_id") public String applicationId;
    public String name;
    public String phone;
    @JsonProperty("line_1") public String line1;
    @JsonProperty("line_2") public String line2;
    public String city;
    public String region;
    @JsonProperty("post_code") public String postCode;
    public String country;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final BankAccountOwnerAddress address = new BankAccountOwnerAddress();
        public Builder id(String id) { address.id = id; return this; }
        public Builder applicationId(String applicationId) { address.applicationId = applicationId; return this; }
        public Builder name(String name) { address.name = name; return this; }
        public Builder phone(String phone) { address.phone = phone; return this; }
        public Builder line1(String line1) { address.line1 = line1; return this; }
        public Builder line2(String line2) { address.line2 = line2; return this; }
        public Builder city(String city) { address.city = city; return this; }
        public Builder region(String region) { address.region = region; return this; }
        public Builder postCode(String postCode) { address.postCode = postCode; return this; }
        public Builder country(String country) { address.country = country; return this; }
        public BankAccountOwnerAddress build() { return address; }
    }
}
