package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PurchaseIntentActivityVisitor {
    @JsonProperty("session_id")
    public String sessionId;
    @JsonProperty("visitor_id")
    public String visitorId;
    @JsonProperty("user_agent")
    public String userAgent;
    public String device;
    public String browser;
    public String os;
    public String country;
    public String region;
    public String city;
    public String timezone;
}
