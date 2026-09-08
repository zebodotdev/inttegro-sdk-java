package com.inttegro.balances;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Per-currency balances available to the Inttegro account. */
public final class BalanceSnapshot {
    private final Map<String, BalanceBreakdown> balances;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public BalanceSnapshot(Map<String, BalanceBreakdown> balances) {
        this.balances = balances == null ? Map.of() : new LinkedHashMap<>(balances);
    }

    public BalanceBreakdown get(String currency) { return balances.get(currency); }
    public int size() { return balances.size(); }
    @JsonValue public Map<String, BalanceBreakdown> values() { return Collections.unmodifiableMap(balances); }
}
