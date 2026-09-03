package com.inttegro.purchaseintents;

import com.inttegro.money.Amount;

/** The resolved price returned with a purchase intent. */
public class PurchaseIntentPrice {
    public Boolean active;
    public String id;
    public String label;
    public Amount nominal;
    public PurchaseIntentOriginalPrice original;
}
