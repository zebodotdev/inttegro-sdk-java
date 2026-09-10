package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmationRequest {
    public String id;
    public String recipient;
    @JsonProperty("sent_via") public PaymentConfirmationChannel sentVia;
    @JsonProperty("token_size") public int tokenSize;
    @JsonProperty("sender_id") public String senderId;
    public String status;
}
