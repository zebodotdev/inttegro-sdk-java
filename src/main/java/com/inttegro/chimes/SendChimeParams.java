package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class SendChimeParams {
    public ChimeRecipient recipient;
    @JsonProperty("full_message") public String fullMessage;
    public ChimeTransport transport;
    public String sender;
    public String purpose;
    @JsonProperty("custom_data") public Map<String, String> customData;
    @JsonProperty("idempotency_key") public String idempotencyKey;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final SendChimeParams params = new SendChimeParams();
        public Builder recipient(ChimeRecipient recipient) { params.recipient = recipient; return this; }
        public Builder fullMessage(String fullMessage) { params.fullMessage = fullMessage; return this; }
        public Builder transport(ChimeTransport transport) { params.transport = transport; return this; }
        public Builder sender(String sender) { params.sender = sender; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
        public Builder idempotencyKey(String idempotencyKey) { params.idempotencyKey = idempotencyKey; return this; }
        public SendChimeParams build() { return params; }
    }
}
