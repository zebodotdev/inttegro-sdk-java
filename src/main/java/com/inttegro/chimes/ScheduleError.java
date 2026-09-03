package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScheduleError {
    public String recipient;
    @JsonProperty("fix_code") public String fixCode;
    public String type;
}
