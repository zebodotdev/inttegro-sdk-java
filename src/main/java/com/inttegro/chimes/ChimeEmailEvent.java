package com.inttegro.chimes;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChimeEmailEvent {
    @JsonProperty("bounce_sub_type") public String bounceSubType;
    @JsonProperty("bounce_type") public String bounceType;
    @JsonProperty("complaint_sub_type") public String complaintSubType;
    public String id;
    @JsonProperty("occurred_at") public OffsetDateTime occurredAt;
    public String provider;
    @JsonProperty("provider_message_id") public String providerMessageId;
    public String type;
}
