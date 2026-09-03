package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentMethodType {
    @JsonProperty("mobile_money") MOBILE_MONEY,
    @JsonProperty("bank_account") BANK_ACCOUNT,
    @JsonProperty("card") CARD,
    @JsonProperty("motito") MOTITO
}
