package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LookupScheduleParams {
    @JsonProperty("schedule_id") public String scheduleId;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final LookupScheduleParams params = new LookupScheduleParams();
        public Builder scheduleId(String scheduleId) { params.scheduleId = scheduleId; return this; }
        public LookupScheduleParams build() { return params; }
    }
}
