package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseIntentActivityVisitor {
    @JsonProperty("session_id")
    public String sessionId;
    @JsonProperty("visitor_id")
    public String visitorId;
    @JsonProperty("user_agent")
    public String userAgent;
    @JsonProperty("ip_address")
    public String ipAddress;
    public String device;
    public String browser;
    public String os;
    public String country;
    public String region;
    public String city;
    public String timezone;
}
