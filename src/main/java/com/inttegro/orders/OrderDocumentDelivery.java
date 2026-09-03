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

public class OrderDocumentDelivery {
    @JsonProperty("document_kind") public String documentKind;
    @JsonProperty("document_url") public String documentUrl;
    @JsonProperty("sent_channels") public List<String> sentChannels;
    @JsonProperty("failed_channels") public List<String> failedChannels;
    public List<OrderDocumentDeliveryAttempt> deliveries;
    public List<OrderDocumentDeliveryAttempt> failures;
}
