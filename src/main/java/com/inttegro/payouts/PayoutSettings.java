package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PayoutSettings {
    @JsonProperty("fx_enabled") public Boolean fxEnabled;
    public PayoutDestinations destinations;
    public PayoutSchedule schedule;
}
