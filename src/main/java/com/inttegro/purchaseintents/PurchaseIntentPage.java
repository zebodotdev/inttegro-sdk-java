package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentPage {
    public Integer number;
    public Integer size;
    @JsonProperty("purchase_intents")
    public List<PurchaseIntent> purchaseIntents;
}
