package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class PageChimesParams {
    @JsonProperty("customer_id") public String customerId;
    @JsonProperty("page_number") public Integer pageNumber;
    @JsonProperty("page_size") public Integer pageSize;
    public String recipient;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PageChimesParams params = new PageChimesParams();
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
        public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
        public Builder recipient(String recipient) { params.recipient = recipient; return this; }
        public PageChimesParams build() { return params; }
    }
}
