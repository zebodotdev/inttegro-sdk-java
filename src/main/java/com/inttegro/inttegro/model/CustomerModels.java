package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CustomerModels {
    public static class CustomerData {
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

    public static class CreateCustomerParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
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

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CreateCustomerParams params = new CreateCustomerParams();
            public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
            public Builder name(String name) { params.name = name; return this; }
            public Builder title(String title) { params.title = title; return this; }
            public Builder suffix(String suffix) { params.suffix = suffix; return this; }
            public Builder reference(String reference) { params.reference = reference; return this; }
            public Builder emailAddress(String emailAddress) {
                params.emailAddress = emailAddress;
                return this;
            }
            public Builder phoneNumber(String phoneNumber) {
                params.phoneNumber = phoneNumber;
                return this;
            }
            public Builder customData(Map<String, String> customData) {
                params.customData = customData;
                return this;
            }
            public CreateCustomerParams build() { return params; }
        }
    }

    public static class LookupCustomerParams {
        @JsonProperty("customer_id")
        public String customerId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupCustomerParams params = new LookupCustomerParams();
            public Builder customerId(String customerId) { params.customerId = customerId; return this; }
            public LookupCustomerParams build() { return params; }
        }
    }

    public static class PageCustomersParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PageCustomersParams params = new PageCustomersParams();
            public Builder pageNumber(Integer pageNumber) {
                params.pageNumber = pageNumber;
                return this;
            }
            public Builder pageSize(Integer pageSize) {
                params.pageSize = pageSize;
                return this;
            }
            public PageCustomersParams build() { return params; }
        }
    }

    public static class Customer {
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

    public static class CustomerResponse {
        public Customer customer;
    }

    public static class CustomersPage {
        public Integer number;
        public Integer size;
        public Customer[] customers;
    }

    public static class CustomersPageResponse {
        public CustomersPage page;
    }

    public static class Address {
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

    public static class BillingDetails {
        public String name;
        @JsonProperty("email_address")
        public String emailAddress;
        @JsonProperty("phone_number")
        public String phoneNumber;
        public Address address;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final BillingDetails details = new BillingDetails();
            public Builder name(String name) { details.name = name; return this; }
            public Builder email(String email) { details.emailAddress = email; return this; }
            public Builder phoneNumber(String phone) { details.phoneNumber = phone; return this; }
            public Builder address(Address addr) { details.address = addr; return this; }
            public BillingDetails build() { return details; }
        }
    }

    public static class Shipping {
        public Address address;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final Shipping shipping = new Shipping();
            public Builder address(Address address) { shipping.address = address; return this; }
            public Shipping build() { return shipping; }
        }
    }
}
