package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodSettings {
    @JsonProperty("mobile_money")
    public PaymentMethodTypeSetting mobileMoney;
    @JsonProperty("bank_account")
    public PaymentMethodTypeSetting bankAccount;
    public PaymentMethodTypeSetting card;
    public PaymentMethodTypeSetting motito;
}
