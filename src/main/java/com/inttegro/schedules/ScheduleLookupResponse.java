package com.inttegro.schedules;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduleLookupResponse {
    @JsonProperty("scheduled_chime") public ScheduleDetail scheduledChime;
}
