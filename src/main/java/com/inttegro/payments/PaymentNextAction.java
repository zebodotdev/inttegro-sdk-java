package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentNextAction {
    public PaymentNextActionType type;
    @JsonProperty("confirm_payment") public ConfirmPaymentAction confirmPayment;
    public RedirectAction redirect;
    public AuthorizeAction authorize;
    @JsonProperty("request_confirmation") public RequestConfirmationAction requestConfirmation;
}
