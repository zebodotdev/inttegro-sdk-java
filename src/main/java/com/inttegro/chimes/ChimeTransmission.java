package com.inttegro.chimes;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class ChimeTransmission {
    public String address;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("delivered_at") public OffsetDateTime deliveredAt;
    @JsonProperty("email_events") public List<ChimeEmailEvent> emailEvents;
    @JsonProperty("email_failure_code") public String emailFailureCode;
    @JsonProperty("email_failure_reason") public String emailFailureReason;
    @JsonProperty("email_status") public String emailStatus;
    public String error;
    @JsonProperty("failed_at") public OffsetDateTime failedAt;
    public String gateway;
    @JsonProperty("gateway_message_id") public String gatewayMessageId;
    public String id;
    @JsonProperty("initialized_at") public OffsetDateTime initializedAt;
    @JsonProperty("last_email_event_at") public OffsetDateTime lastEmailEventAt;
    public ChimeTransport mechanism;
    @JsonProperty("sent_at") public OffsetDateTime sentAt;
    @JsonProperty("sent_via") public ChimeTransport sentVia;
    public String status;
    @JsonProperty("suppressed_at") public OffsetDateTime suppressedAt;
    @JsonProperty("suppression_reason") public String suppressionReason;
}
