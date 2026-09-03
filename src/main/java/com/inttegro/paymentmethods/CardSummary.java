package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class CardSummary {
    public String brand;
    @JsonProperty("expires_on")
    public String expiresOn;
    public CardParty issuer;
    public CardParty owner;
    public String type;
}
