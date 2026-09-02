package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LookupRefundParams {
    @JsonProperty("refund_id") public String refundId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupRefundParams params = new LookupRefundParams();
        public Builder refundId(String refundId) { params.refundId = refundId; return this; }
        public LookupRefundParams build() { return params; }
    }
}
