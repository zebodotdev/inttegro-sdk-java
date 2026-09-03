package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PayoutPageParams {
    @JsonProperty("page_number") public Integer pageNumber;
    @JsonProperty("page_size") public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PayoutPageParams params = new PayoutPageParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public PayoutPageParams build() { return params; }
    }
}
