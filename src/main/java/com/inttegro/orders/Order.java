package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.customers.BillingDetails;
import com.inttegro.customers.CustomerData;
import com.inttegro.customers.Shipping;
import com.inttegro.refunds.Refund;

import java.util.List;

/**
 * Canonical order domain model returned by order lifecycle operations.
 *
 * <p>Transport envelopes are decoded inside {@code OrdersClient}; callers work
 * with this resource directly.</p>
 */
public class Order {
    public String id;
    public OrderStatus status;
    public String number;
    @JsonProperty("receipt_number") public String receiptNumber;
    @JsonProperty("customer_id") public String customerId;
    public CustomerData customer;
    @JsonProperty("billing_details") public BillingDetails billingDetails;
    public Shipping shipping;
    @JsonProperty("line_items") public List<OrderLineItem> lineItems;
    @JsonProperty("line_item_group") public LineItemGroup lineItemGroup;
    public Payment payment;
    @JsonProperty("payment_status") public OrderPaymentStatus paymentStatus;
    @JsonProperty("payment_method_id") public String paymentMethodId;
    @JsonProperty("statement_descriptor") public String statementDescriptor;
    @JsonProperty("checkout_settings") public CheckoutSettings checkoutSettings;
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("sealed_at") public String sealedAt;
    @JsonProperty("completed_at") public String completedAt;
    @JsonProperty("expires_at") public String expiresAt;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("updated_at") public String updatedAt;
    @JsonProperty("paid_at") public String paidAt;
    @JsonProperty("cancelled_at") public String cancelledAt;
    public Invoice invoice;
    public List<Refund> refunds;
}
