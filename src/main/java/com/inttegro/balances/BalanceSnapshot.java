package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** GHS balances available to the Inttegro account. */
public final class BalanceSnapshot {
    public final BalanceBreakdown ghs;

    @JsonCreator
    public BalanceSnapshot(@JsonProperty(value = "ghs", required = true) BalanceBreakdown ghs) {
        this.ghs = Objects.requireNonNull(ghs, "ghs");
    }
}
