package com.inttegro.payments;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizeAction {
    public String beneficiary;
    @JsonProperty("expires_at") public OffsetDateTime expiresAt;
    public String scheme;
}
