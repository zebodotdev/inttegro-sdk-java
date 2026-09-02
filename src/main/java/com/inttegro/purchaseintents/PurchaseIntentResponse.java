package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentResponse {
    @JsonProperty("purchase_intent")
    public PurchaseIntent purchaseIntent;
    public Object error;
}
