package com.inttegro.payments;

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

public class ConfirmPaymentRequest {
    public String id;
    public String recipient;
    @JsonProperty("sent_via") public PaymentConfirmationChannel sentVia;
    @JsonProperty("token_size") public Integer tokenSize;
    @JsonProperty("sender_id") public String senderId;
}
