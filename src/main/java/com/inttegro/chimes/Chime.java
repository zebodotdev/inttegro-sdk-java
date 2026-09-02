package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class Chime {
    public String id;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("full_message") public String fullMessage;
    public ChimeRecipient recipient;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public Object delivery;
    public Object transmission;
}
