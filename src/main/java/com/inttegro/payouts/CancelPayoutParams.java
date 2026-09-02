package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;
import java.util.Map;

public class CancelPayoutParams {
    @JsonProperty("payout_id") public String payoutId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CancelPayoutParams params = new CancelPayoutParams();
        public Builder payoutId(String payoutId) { params.payoutId = payoutId; return this; }
        public CancelPayoutParams build() { return params; }
    }
}
