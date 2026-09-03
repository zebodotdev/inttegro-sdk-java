package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class LookupPayoutParams {
    @JsonProperty("payout_id") public String payoutId;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final LookupPayoutParams params = new LookupPayoutParams();
        public Builder payoutId(String payoutId) { params.payoutId = payoutId; return this; }
        public LookupPayoutParams build() { return params; }
    }
}
