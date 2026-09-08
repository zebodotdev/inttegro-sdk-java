package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;

/** Balance value and observation time for one currency. */
public final class CustomerBalanceValue {
    @JsonProperty("as_of")
    public String asOf;
    public Amount available;
}
