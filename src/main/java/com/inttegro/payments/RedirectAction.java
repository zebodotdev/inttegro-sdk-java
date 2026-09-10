package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedirectAction {
    @JsonProperty("valid_until") public OffsetDateTime validUntil;
    @JsonProperty("latest_visit") public RedirectVisit latestVisit;
    @JsonProperty("redirect_url") public String redirectUrl;
}
