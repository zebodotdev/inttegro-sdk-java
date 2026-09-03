package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentNextActionType {
    @JsonProperty("confirm_payment") CONFIRM_PAYMENT,
    @JsonProperty("execute") EXECUTE,
    @JsonProperty("redirect") REDIRECT,
    @JsonProperty("authorize") AUTHORIZE,
    @JsonProperty("none") NONE
}
