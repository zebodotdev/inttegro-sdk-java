package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PurchaseIntentActivityAttribution {
    @JsonProperty("landing_url")
    public String landingUrl;
    public String referrer;
    @JsonProperty("referrer_host")
    public String referrerHost;
    public String source;
    public String medium;
    public String campaign;
    public String term;
    public String content;
    public String channel;
}
