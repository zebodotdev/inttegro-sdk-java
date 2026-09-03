package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.LineItemType;
import com.inttegro.common.PaymentMethodType;
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

public class OrderPayoutFinancialAccount {
    public String type;
    public OrderPayoutWallet wallet;
    @JsonProperty("bank_account") public com.inttegro.financialaccounts.BankAccountConfig bankAccount;
    @JsonProperty("dosh_account") public Map<String, Object> doshAccount;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayoutFinancialAccount account = new OrderPayoutFinancialAccount();
        public Builder type(String type) { account.type = type; return this; }
        public Builder wallet(OrderPayoutWallet wallet) { account.wallet = wallet; return this; }
        public Builder bankAccount(com.inttegro.financialaccounts.BankAccountConfig bankAccount) { account.bankAccount = bankAccount; return this; }
        public Builder doshAccount(Map<String, Object> doshAccount) { account.doshAccount = doshAccount; return this; }
        public OrderPayoutFinancialAccount build() { return account; }
    }
}
