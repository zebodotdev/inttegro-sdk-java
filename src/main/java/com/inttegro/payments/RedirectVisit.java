package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedirectVisit {
    @JsonProperty("user_agent") public String userAgent;
    @JsonProperty("ip_address") public String ipAddress;
    public OffsetDateTime at;
}
