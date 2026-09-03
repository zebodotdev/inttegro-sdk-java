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

public class PaymentAttempt {
    @JsonProperty("payment_method_type") public String paymentMethodType;
    @JsonProperty("payment_method_id") public String paymentMethodId;
    public String reference;
    public PaymentAttemptStatus status;
    @JsonProperty("initiated_at") public String initiatedAt;
    @JsonProperty("succeeded_at") public String succeededAt;
    @JsonProperty("failed_at") public String failedAt;
}
