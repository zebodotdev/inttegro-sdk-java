package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PayoutSchedule {
    public String id;
    public String name;
    public String type;
    public String interval;
    @JsonProperty("schedule_on") public String scheduleOn;
    public String description;
    public PayoutScheduleSpec spec;
}
