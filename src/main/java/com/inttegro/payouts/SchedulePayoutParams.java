package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class SchedulePayoutParams {
    @JsonProperty("destination_id") public String destinationId;
    @JsonProperty("execute_after") public String executeAfter;
    @JsonProperty("max_amount") public Long maxAmount;
    public String reference;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final SchedulePayoutParams params = new SchedulePayoutParams();
        public Builder destinationId(String destinationId) { params.destinationId = destinationId; return this; }
        public Builder executeAfter(String executeAfter) { params.executeAfter = executeAfter; return this; }
        public Builder maxAmount(Long maxAmount) { params.maxAmount = maxAmount; return this; }
        public Builder reference(String reference) { params.reference = reference; return this; }
        public SchedulePayoutParams build() { return params; }
    }
}
