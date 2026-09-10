package com.inttegro.balances;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;

public class BalanceTransaction {
    public String id;
    public BalanceTransactionType type;
    @JsonProperty("payment_id") public String paymentId;
    @JsonProperty("refund_id") public String refundId;
    @JsonProperty("payout_id") public String payoutId;
    @JsonProperty("order_id") public String orderId;
    public Amount amount;
    @JsonProperty("available_at") public OffsetDateTime availableAt;
    @JsonProperty("claimed_at") public OffsetDateTime claimedAt;
    @JsonProperty("paid_at") public OffsetDateTime paidAt;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
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
