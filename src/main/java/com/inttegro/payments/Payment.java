package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.balances.BalanceTransaction;
import com.inttegro.balances.PayoutConfiguration;
import com.inttegro.money.Amount;
import java.util.List;

/** Payment projection returned inside an order. */
public class Payment {
    public String id;
    @JsonProperty("statement_descriptor") public String statementDescriptor;
    @JsonProperty("payment_method_types") public List<String> paymentMethodTypes;
    @JsonProperty("payment_method") public PaymentMethodSnapshot paymentMethod;
    @JsonProperty("billing_details") public PaymentBillingDetails billingDetails;
    public PaymentCustomer customer;
    @JsonProperty("latest_attempt") public PaymentAttempt latestAttempt;
    public Amount amount;
    @JsonProperty("next_action") public PaymentNextAction nextAction;
    @JsonProperty("latest_error") public PaymentError latestError;
    @JsonProperty("balance_transaction") public BalanceTransaction balanceTransaction;
    @JsonProperty("payout_configuration") public PayoutConfiguration payoutConfiguration;
    public PaymentStatus status;
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("executed_at") public OffsetDateTime executedAt;
    @JsonProperty("due_at") public OffsetDateTime dueAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    @JsonProperty("expired_at") public OffsetDateTime expiredAt;
    @JsonProperty("paid_at") public OffsetDateTime paidAt;
    @JsonProperty("paid_offline") public Boolean paidOffline;
    @JsonProperty("failed_at") public OffsetDateTime failedAt;

    /** Whether the payment completed successfully. */
    public boolean isPaid() { return status == PaymentStatus.PAID; }

    /** Whether the payment is waiting for customer or merchant action. */
    public boolean requiresAction() { return status == PaymentStatus.REQUIRES_ACTION; }

    /** Whether the payment has reached a final state. */
    public boolean isTerminal() {
        return status == PaymentStatus.PAID
                || status == PaymentStatus.CANCELED
                || status == PaymentStatus.EXPIRED
                || status == PaymentStatus.FAILED;
    }

    /** Action details when the payment currently requires action. */
    public PaymentNextAction requiredAction() { return requiresAction() ? nextAction : null; }
}
