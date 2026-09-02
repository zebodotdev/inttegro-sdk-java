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

public class OrderUpdateParams {
    @JsonProperty("order_id") public String orderId;
    @JsonProperty("clear_payment_method") public Boolean clearPaymentMethod;
    @JsonProperty("custom_data") public Map<String, String> customData;
    @JsonProperty("invoice_settings") public Map<String, Object> invoiceSettings;
    public Boolean finalize;
    @JsonProperty("line_items") public List<OrderLineItem> lineItems;
    public String number;
    @JsonProperty("receipt_number") public String receiptNumber;
    @JsonProperty("payment_method_data") public com.inttegro.paymentmethods.PaymentMethodData paymentMethodData;
    @JsonProperty("payment_method_id") public String paymentMethodId;
    @JsonProperty("statement_descriptor") public String statementDescriptor;
    @JsonProperty("statement_descriptor_prefix") public String statementDescriptorPrefix;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final OrderUpdateParams params = new OrderUpdateParams();
        public Builder orderId(String orderId) { params.orderId = orderId; return this; }
        public Builder clearPaymentMethod(Boolean clearPaymentMethod) { params.clearPaymentMethod = clearPaymentMethod; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder invoiceSettings(Map<String, Object> invoiceSettings) { params.invoiceSettings = invoiceSettings; return this; }
        public Builder finalizeOrder(Boolean finalize) { params.finalize = finalize; return this; }
        public Builder lineItems(List<OrderLineItem> lineItems) { params.lineItems = lineItems; return this; }
        public Builder number(String number) { params.number = number; return this; }
        public Builder receiptNumber(String receiptNumber) { params.receiptNumber = receiptNumber; return this; }
        public Builder paymentMethodData(com.inttegro.paymentmethods.PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
        public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
        public Builder statementDescriptor(String statementDescriptor) { params.statementDescriptor = statementDescriptor; return this; }
        public Builder statementDescriptorPrefix(String statementDescriptorPrefix) { params.statementDescriptorPrefix = statementDescriptorPrefix; return this; }
        public OrderUpdateParams build() { return params; }
    }
}
