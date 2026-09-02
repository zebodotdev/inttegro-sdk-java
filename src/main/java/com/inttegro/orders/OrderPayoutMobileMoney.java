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
import com.inttegro.paymentmethods.PaymentMethodObject;
import com.inttegro.refunds.Refund;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OrderPayoutMobileMoney {
    @JsonProperty("account_number")
    public String accountNumber;
    public String network;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayoutMobileMoney mobileMoney = new OrderPayoutMobileMoney();
        public Builder accountNumber(String accountNumber) { mobileMoney.accountNumber = accountNumber; return this; }
        public Builder network(String network) { mobileMoney.network = network; return this; }
        public OrderPayoutMobileMoney build() { return mobileMoney; }
    }
}
