package com.inttegro.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduleCancelResponse {
    @JsonProperty("scheduled_chime") public ScheduleDetail scheduledChime;
}
