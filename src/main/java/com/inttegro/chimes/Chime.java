package com.inttegro.chimes;

import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Chime {
    public String id;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("full_message") public String fullMessage;
    public ChimeRecipient recipient;
    @JsonProperty("sender_id") public String senderId;
    public String purpose;
    @JsonProperty("custom_data") public CustomData customData;
    public ChimeEmailMessage email;
    public ChimeTransmission transmission;
}
