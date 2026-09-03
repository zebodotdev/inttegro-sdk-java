package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.customers.Address;
import com.inttegro.customers.BillingDetails;
import com.inttegro.customers.CustomerData;
import com.inttegro.customers.Shipping;
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.refunds.Refund;
import com.inttegro.financialaccounts.WalletType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OrderPayoutWallet {
    public WalletType type;
    @JsonProperty("mobile_money")
    public OrderPayoutMobileMoney mobileMoney;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderPayoutWallet wallet = new OrderPayoutWallet();
        public Builder type(WalletType type) { wallet.type = type; return this; }
        public Builder mobileMoney(OrderPayoutMobileMoney mobileMoney) { wallet.mobileMoney = mobileMoney; return this; }
        public OrderPayoutWallet build() { return wallet; }
    }
}
