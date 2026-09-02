package com.inttegro.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduleResponse {
    @JsonProperty("scheduled_chime") public ScheduledChime scheduledChime;
}
