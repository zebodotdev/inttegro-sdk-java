package com.inttegro.broadcasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BroadcastChimeParams {
    public List<String> recipients;
    @JsonProperty("message_template") public String messageTemplate;
    @JsonProperty("service_name") public String serviceName;
    public String sender;
    public String purpose;
    @JsonProperty("preferred_gateway") public String preferredGateway;
    @JsonProperty("idempotency_key") public String idempotencyKey;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final BroadcastChimeParams params = new BroadcastChimeParams();
        public Builder recipients(List<String> recipients) { params.recipients = recipients; return this; }
        public Builder messageTemplate(String messageTemplate) { params.messageTemplate = messageTemplate; return this; }
        public Builder serviceName(String serviceName) { params.serviceName = serviceName; return this; }
        public Builder sender(String sender) { params.sender = sender; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder preferredGateway(String preferredGateway) { params.preferredGateway = preferredGateway; return this; }
        public Builder idempotencyKey(String idempotencyKey) { params.idempotencyKey = idempotencyKey; return this; }
        public BroadcastChimeParams build() { return params; }
    }
}
