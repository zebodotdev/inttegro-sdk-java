package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PayoutSettings {
    @JsonProperty("fx_enabled") public Boolean fxEnabled;
    public Map<String, String> destinations;
    public PayoutSchedule schedule;
}
