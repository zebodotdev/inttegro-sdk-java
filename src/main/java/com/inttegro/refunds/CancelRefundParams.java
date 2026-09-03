package com.inttegro.refunds;

import com.inttegro.RequestMeta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CancelRefundParams {
    @JsonProperty("refund_id") public String refundId;
    @JsonProperty("request_meta") public RequestMeta requestMeta;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CancelRefundParams params = new CancelRefundParams();
        public Builder refundId(String refundId) { params.refundId = refundId; return this; }
        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public CancelRefundParams build() { return params; }
    }
}
