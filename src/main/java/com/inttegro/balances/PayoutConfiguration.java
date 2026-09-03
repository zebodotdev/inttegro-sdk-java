package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class PayoutConfiguration {
    @JsonProperty("enable_fx") public Boolean enableFx;
    public PayoutConfigurationDestination destination;
}
