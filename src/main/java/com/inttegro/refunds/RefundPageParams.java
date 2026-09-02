package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RefundPageParams {
    @JsonProperty("page_number") public Integer pageNumber;
    @JsonProperty("page_size") public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final RefundPageParams params = new RefundPageParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public RefundPageParams build() { return params; }
    }
}
