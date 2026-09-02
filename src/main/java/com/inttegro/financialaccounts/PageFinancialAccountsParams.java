package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PageFinancialAccountsParams {
    @JsonProperty("page_number") public Integer pageNumber;
    @JsonProperty("page_size") public Integer pageSize;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PageFinancialAccountsParams params = new PageFinancialAccountsParams();
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public PageFinancialAccountsParams build() { return params; }
    }
}
