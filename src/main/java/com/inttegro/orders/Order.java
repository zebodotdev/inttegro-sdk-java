package com.inttegro.orders;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.CustomData;
import com.inttegro.payments.Payment;
import com.inttegro.refunds.Refund;
import java.util.List;

/** Complete public order projection returned by the API. */
public class Order {
    public String id;
    public OrderStatus status;
    public String number;
    @JsonProperty("receipt_number") public String receiptNumber;
    public String reference;
    public OrderCustomer customer;
    @JsonProperty("checkout_settings") public CheckoutSettings checkoutSettings;
    @JsonProperty("invoice_settings") public InvoiceSettings invoiceSettings;
    public Invoice invoice;
    @JsonProperty("line_item_group") public LineItemGroup lineItemGroup;
    public Payment payment;
    @JsonProperty("custom_data") public CustomData customData;
    @JsonProperty("created_from") public OrderCreatedFrom createdFrom;
    @JsonProperty("initiated_at") public OffsetDateTime initiatedAt;
    @JsonProperty("completed_at") public OffsetDateTime completedAt;
    @JsonProperty("sealed_at") public OffsetDateTime sealedAt;
    @JsonProperty("paid_at") public OffsetDateTime paidAt;
    @JsonProperty("canceled_at") public OffsetDateTime canceledAt;
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    @JsonProperty("payment_due_at") public OffsetDateTime paymentDueAt;
    public List<Refund> refunds;
}
