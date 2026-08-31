package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.Money;
import java.util.Map;

public class BalanceModels {
    public enum BalanceTransactionType {
        @JsonProperty("payment")
        PAYMENT,
        @JsonProperty("refund")
        REFUND
    }

    public static class PayoutConfigurationDestination {
        @JsonProperty("financial_account_id") public String financialAccountId;
    }

    public static class PayoutConfiguration {
        @JsonProperty("enable_fx") public Boolean enableFx;
        public PayoutConfigurationDestination destination;
    }

    public static class BalanceTransaction {
        public String id;
        public BalanceTransactionType type;
        @JsonProperty("payment_id") public String paymentId;
        @JsonProperty("refund_id") public String refundId;
        @JsonProperty("payout_id") public String payoutId;
        @JsonProperty("order_id") public String orderId;
        public Money amount;
        @Deprecated
        @JsonProperty("amount_expected") public Money amountExpected;
        @Deprecated
        @JsonProperty("amount_available") public Money amountAvailable;
        @JsonProperty("available_at") public String availableAt;
        @JsonProperty("claimed_at") public String claimedAt;
        @JsonProperty("paid_at") public String paidAt;
        @JsonProperty("created_at") public String createdAt;
        @Deprecated
        @JsonProperty("payout_configuration") public PayoutConfiguration payoutConfiguration;

        public String sourceId() {
            if (type == BalanceTransactionType.PAYMENT && paymentId != null && !paymentId.isBlank() && refundId == null) {
                return paymentId;
            }
            if (type == BalanceTransactionType.REFUND && refundId != null && !refundId.isBlank() && paymentId == null) {
                return refundId;
            }
            return null;
        }
    }

    public static class BalanceTransactionPageParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final BalanceTransactionPageParams params = new BalanceTransactionPageParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public BalanceTransactionPageParams build() { return params; }
        }
    }

    public static class BalanceTransactionLookupParams {
        @JsonProperty("transaction_id") public String transactionId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final BalanceTransactionLookupParams params = new BalanceTransactionLookupParams();
            public Builder transactionId(String transactionId) { params.transactionId = transactionId; return this; }
            public BalanceTransactionLookupParams build() { return params; }
        }
    }

    public static class BalanceTransactionPageResponse {
        public com.zebodotdev.commerce.model.OrderModels.Page<BalanceTransaction> page;
    }

    public static class BalanceTransactionResponse {
        public BalanceTransaction transaction;
    }

    public static class BalanceAmount {
        public Long amount;
    }

    public static class BalanceBreakdown {
        public BalanceAmount available;
        public BalanceAmount pending;
        public BalanceAmount reserved;
        public BalanceAmount refund;
        @JsonProperty("includes_transactions_before") public String includesTransactionsBefore;
    }

    public static class BalancesResponse {
        public Map<String, BalanceBreakdown> balances;
    }
}
