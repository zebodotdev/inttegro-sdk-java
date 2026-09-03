package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CancelScheduleParams {
    @JsonProperty("schedule_id") public String scheduleId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CancelScheduleParams params = new CancelScheduleParams();
        public Builder scheduleId(String scheduleId) { params.scheduleId = scheduleId; return this; }
        public CancelScheduleParams build() { return params; }
    }
}
