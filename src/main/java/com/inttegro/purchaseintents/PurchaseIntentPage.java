package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PurchaseIntentPage {
    public int number;
    public int size;
    @JsonProperty("purchase_intents")
    public List<PurchaseIntent> purchaseIntents;
}
