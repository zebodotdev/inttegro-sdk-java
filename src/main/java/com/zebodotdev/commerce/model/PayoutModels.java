package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.Money;

import java.util.List;
import java.util.Map;

public class PayoutModels {
    public static class PayoutScheduleSpec {
        public String id;
        @JsonProperty("t_plus") public String tPlus;
        public String label;
        public String abide;
    }

    public static class PayoutSchedule {
        public String id;
        public String name;
        public String type;
        public String interval;
        @JsonProperty("schedule_on") public String scheduleOn;
        public String description;
        public PayoutScheduleSpec spec;
    }

    public static class PayoutSettings {
        @JsonProperty("fx_enabled") public Boolean fxEnabled;
        public Map<String, String> destinations;
        public PayoutSchedule schedule;
    }

    public static class PayoutSettingsResponse { public PayoutSettings settings; }

    public static class PayoutPageParams {
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
    public static class CancelPayoutParams {
        @JsonProperty("payout_id") public String payoutId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CancelPayoutParams params = new CancelPayoutParams();
            public Builder payoutId(String payoutId) { params.payoutId = payoutId; return this; }
            public CancelPayoutParams build() { return params; }
        }
    }
    public static class LookupPayoutParams {
        @JsonProperty("payout_id") public String payoutId;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final LookupPayoutParams params = new LookupPayoutParams();
            public Builder payoutId(String payoutId) { params.payoutId = payoutId; return this; }
            public LookupPayoutParams build() { return params; }
        }
    }
    public static class SchedulePayoutParams {
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

    public static class Payout {
        public String id;
        @JsonProperty("application_id") public String applicationId;
        @JsonProperty("destination_id") public String destinationId;
        public Money amount;
        @JsonProperty("max_amount") public Money maxAmount;
        public String status;
        @JsonProperty("initiated_by") public String initiatedBy;
        @JsonProperty("execute_after") public String executeAfter;
        @JsonProperty("scheduled_at") public String scheduledAt;
        @JsonProperty("canceled_at") public String canceledAt;
        @JsonProperty("latest_attempt_id") public String latestAttemptId;
        @JsonProperty("latest_error") public Object latestError;
        @JsonProperty("initiated_at") public String initiatedAt;
        @JsonProperty("executed_at") public String executedAt;
        @JsonProperty("expected_at") public String expectedAt;
        @JsonProperty("succeeded_at") public String succeededAt;
        @JsonProperty("balance_transaction_ids") public List<String> balanceTransactionIds;
    }

    public static class PayoutPageResponse { public com.zebodotdev.commerce.model.OrderModels.Page<Payout> page; }
    public static class PayoutResponse { public Payout payout; }
    public static class CancelPayoutResponse { public Payout payout; }
}
