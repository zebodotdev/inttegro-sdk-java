package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
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

public class OrderPayoutDestination {
    @JsonProperty("financial_account_id")
    public String financialAccountId;
    @JsonProperty("financial_account_data")
    public OrderPayoutFinancialAccount financialAccountData;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayoutDestination destination = new OrderPayoutDestination();
        public Builder financialAccountId(String financialAccountId) { destination.financialAccountId = financialAccountId; return this; }
        public Builder financialAccountData(OrderPayoutFinancialAccount financialAccountData) { destination.financialAccountData = financialAccountData; return this; }
        public OrderPayoutDestination build() { return destination; }
    }
}
