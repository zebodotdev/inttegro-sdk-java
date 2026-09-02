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

public class OrderLineItem {
    public LineItemType type;
    public ProductLineItem product;
    public FeeLineItem fee;
    public ShippingLineItem shipping;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final OrderLineItem item = new OrderLineItem();
        public Builder type(LineItemType type) { item.type = type; return this; }
        public Builder product(ProductLineItem product) { item.product = product; return this; }
        public Builder fee(FeeLineItem fee) { item.fee = fee; return this; }
        public Builder shipping(ShippingLineItem shipping) { item.shipping = shipping; return this; }
        public OrderLineItem build() { return item; }
    }

    public static OrderLineItem product(ProductLineItem product) {
        OrderLineItem li = new OrderLineItem();
        li.type = LineItemType.PRODUCT;
        li.product = product;
        return li;
    }

    public static OrderLineItem product(Consumer<ProductLineItem.Builder> fn) {
        ProductLineItem.Builder b = new ProductLineItem.Builder();
        fn.accept(b);
        return product(b.build());
    }

    public static OrderLineItem fee(FeeLineItem fee) {
        OrderLineItem li = new OrderLineItem();
        li.type = LineItemType.FEE;
        li.fee = fee;
        return li;
    }

    public static OrderLineItem fee(Consumer<FeeLineItem> fn) {
        FeeLineItem f = new FeeLineItem();
        fn.accept(f);
        return fee(f);
    }

    public static OrderLineItem shipping(ShippingLineItem shipping) {
        OrderLineItem li = new OrderLineItem();
        li.type = LineItemType.SHIPPING;
        li.shipping = shipping;
        return li;
    }

    public static OrderLineItem shipping(Consumer<ShippingLineItem> fn) {
        ShippingLineItem s = new ShippingLineItem();
        fn.accept(s);
        return shipping(s);
    }
}
