package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PaymentMethodTypeSetting {
    public PaymentMethodType type;
    public String name;
    public String description;
    public Boolean enabled;
    @JsonProperty("confirms_use")
    public Boolean confirmsUse;
}
