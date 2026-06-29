package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebodotdev.commerce.model.CommonModels.Money;

public class BalanceModels {
    public static class PayoutConfigurationDestination {
        @JsonProperty("financial_account_id") public String financialAccountId;
    }

    public static class PayoutConfiguration {
        @JsonProperty("enable_fx") public Boolean enableFx;
        public PayoutConfigurationDestination destination;
    }

    public static class BalanceTransaction {
        public String id;
        @JsonProperty("payment_id") public String paymentId;
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("amount_expected") public Money amountExpected;
        @JsonProperty("amount_available") public Money amountAvailable;
        @JsonProperty("available_at") public String availableAt;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("payout_configuration") public PayoutConfiguration payoutConfiguration;
    }

    public static class BalanceTransactionPageParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;
    }

    public static class BalanceTransactionPageResponse {
        public com.zebodotdev.commerce.model.OrderModels.Page<BalanceTransaction> page;
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
