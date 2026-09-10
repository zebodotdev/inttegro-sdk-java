package com.inttegro.customers;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.money.Amount;

/** Balance value and observation time for one currency. */
public final class CustomerBalanceValue {
    @JsonProperty("as_of")
    public OffsetDateTime asOf;
    public Amount available;
}
