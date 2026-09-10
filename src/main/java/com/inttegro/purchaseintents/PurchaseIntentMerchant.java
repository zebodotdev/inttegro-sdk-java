package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseIntentMerchant {
    @JsonProperty("app_name")
    public String appName;
    @JsonProperty("organization_id")
    public String organizationId;
    @JsonProperty("organization_name")
    public String organizationName;
}
