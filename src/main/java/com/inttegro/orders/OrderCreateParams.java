package com.inttegro.orders;

import com.inttegro.RequestMeta;

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

public class OrderCreateParams {
    @JsonProperty("request_meta")
    public RequestMeta requestMeta;
    @JsonProperty("customer_data")
    public CustomerData customerData;
    @JsonProperty("customer_id")
    public String customerId;
    @JsonProperty("payment_method_id")
    public String paymentMethodId;
    @JsonProperty("payment_method_data")
    public com.inttegro.paymentmethods.PaymentMethodData paymentMethodData;
    @JsonProperty("statement_descriptor")
    public String statementDescriptor;
    @JsonProperty("statement_descriptor_prefix")
    public String statementDescriptorPrefix;
    @JsonProperty("execute_payment")
    public Boolean executePayment;
    public Boolean finalize;
    @Deprecated
    @JsonProperty("idempotency_key")
    public String idempotencyKey;
    @JsonProperty("checkout_settings")
    public CheckoutSettings checkoutSettings;
    @JsonProperty("payout_settings")
    public OrderPayoutSettings payoutSettings;
    public String number;
    @JsonProperty("receipt_number")
    public String receiptNumber;
    @JsonProperty("line_items")
    public List<OrderLineItem> lineItems;
    @JsonProperty("custom_data")
    public Map<String, String> customData;
    @JsonProperty("billing_details")
    public BillingDetails billingDetails;
    public Shipping shipping;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final OrderCreateParams params = new OrderCreateParams();
        private final List<OrderLineItem> items = new ArrayList<>();
        public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
        public Builder customerData(CustomerData customer) { params.customerData = customer; return this; }
        public Builder customerId(String customerId) { params.customerId = customerId; return this; }
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public Builder paymentMethodData(com.inttegro.paymentmethods.PaymentMethodData data) { params.paymentMethodData = data; return this; }
        public Builder statementDescriptor(String desc) { params.statementDescriptor = desc; return this; }
        public Builder statementDescriptorPrefix(String prefix) { params.statementDescriptorPrefix = prefix; return this; }
        public Builder executePayment(boolean execute) { params.executePayment = execute; return this; }
        public Builder finalizeOrder(boolean finalize) { params.finalize = finalize; return this; }
        @Deprecated
        public Builder idempotencyKey(String key) { params.idempotencyKey = key; return this; }
        public Builder checkoutSettings(CheckoutSettings settings) { params.checkoutSettings = settings; return this; }
        public Builder payoutSettings(OrderPayoutSettings settings) { params.payoutSettings = settings; return this; }
        public Builder number(String number) { params.number = number; return this; }
        public Builder receiptNumber(String receiptNumber) { params.receiptNumber = receiptNumber; return this; }
        public Builder lineItem(OrderLineItem item) { this.items.add(item); return this; }
        public Builder customData(Map<String, String> data) { params.customData = data; return this; }
        public Builder billingDetails(BillingDetails details) { params.billingDetails = details; return this; }
        public Builder shipping(Shipping shipping) { params.shipping = shipping; return this; }
        public OrderCreateParams build() { params.lineItems = items; return params; }
    }
}
