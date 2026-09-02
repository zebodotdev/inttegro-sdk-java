package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodTypeSetting {
    public String type;
    public String name;
    public String description;
    public Boolean enabled;
    @JsonProperty("confirms_use")
    public Boolean confirmsUse;
}
