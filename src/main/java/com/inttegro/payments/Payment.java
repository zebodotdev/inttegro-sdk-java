package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;
import com.inttegro.customers.Address;
import com.inttegro.customers.BillingDetails;
import com.inttegro.customers.CustomerData;
import com.inttegro.customers.Shipping;
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Payment {
    public String id;
    public PaymentStatus status;
    @JsonProperty("statement_descriptor") public String statementDescriptor;
    public Amount amount;
    @JsonProperty("payment_method") public PaymentMethod paymentMethod;
    @JsonProperty("latest_attempt") public PaymentAttempt latestAttempt;
    @JsonProperty("next_action") public PaymentNextAction nextAction;
    @JsonProperty("balance_transaction") public com.inttegro.balances.BalanceTransaction balanceTransaction;
    @JsonProperty("payout_configuration") public com.inttegro.balances.PayoutConfiguration payoutConfiguration;
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("executed_at") public String executedAt;
    @JsonProperty("paid_at") public String paidAt;
    @JsonProperty("failed_at") public String failedAt;
}
