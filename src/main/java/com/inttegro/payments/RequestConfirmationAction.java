package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestConfirmationAction {
    @JsonProperty("last_request") public ConfirmationRequest lastRequest;
    public OffsetDateTime after;
}
