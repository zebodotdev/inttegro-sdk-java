package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PagePaymentMethodsParams {
    @JsonProperty("customer_id")
    public String customerId;
    @JsonProperty("page_number")
    public Integer pageNumber;
    @JsonProperty("page_size")
    public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PagePaymentMethodsParams params = new PagePaymentMethodsParams();
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public PagePaymentMethodsParams build() { return params; }
    }
}
