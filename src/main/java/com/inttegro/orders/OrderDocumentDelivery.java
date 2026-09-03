package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

public class OrderDocumentDelivery {
    @JsonProperty("document_kind") public OrderDocumentKind documentKind;
    @JsonProperty("document_url") public String documentUrl;
    @JsonProperty("sent_channels") public List<DeliveryChannel> sentChannels;
    @JsonProperty("failed_channels") public List<DeliveryChannel> failedChannels;
    public List<OrderDocumentDeliveryAttempt> deliveries;
    public List<OrderDocumentDeliveryAttempt> failures;
}
