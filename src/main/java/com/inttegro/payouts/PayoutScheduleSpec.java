package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PayoutScheduleSpec {
    public String id;
    @JsonProperty("t_plus") public String tPlus;
    public String label;
    public String abide;
}
