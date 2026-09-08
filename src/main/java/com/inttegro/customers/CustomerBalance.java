package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Available customer balances keyed by lowercase currency code. */
public final class CustomerBalance {
    private final Map<String, CustomerBalanceValue> values;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CustomerBalance(Map<String, CustomerBalanceValue> values) {
        this.values = values == null ? Map.of() : new LinkedHashMap<>(values);
    }

    public CustomerBalanceValue get(String currency) { return values.get(currency); }
    public int size() { return values.size(); }

    @JsonValue
    public Map<String, CustomerBalanceValue> values() {
        return Collections.unmodifiableMap(values);
    }
}
