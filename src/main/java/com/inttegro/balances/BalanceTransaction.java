package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import java.util.Map;

public class BalanceTransaction {
    public String id;
    public BalanceTransactionType type;
    @JsonProperty("payment_id") public String paymentId;
    @JsonProperty("refund_id") public String refundId;
    @JsonProperty("payout_id") public String payoutId;
    @JsonProperty("order_id") public String orderId;
    public Amount amount;
    @Deprecated
    @JsonProperty("amount_expected") public Amount amountExpected;
    @Deprecated
    @JsonProperty("amount_available") public Amount amountAvailable;
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
