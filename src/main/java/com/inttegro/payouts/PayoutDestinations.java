package com.inttegro.payouts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Currency-to-financial-account payout routing. */
public final class PayoutDestinations {
    private final Map<String, String> values = new LinkedHashMap<>();

    public PayoutDestinations() {}
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public PayoutDestinations(Map<String, String> values) {
        if (values != null) values.forEach(this::set);
    }
    public PayoutDestinations set(String currency, String financialAccountId) {
        if (currency == null || currency.isBlank()) throw new IllegalArgumentException("currency cannot be blank");
        if (financialAccountId == null || financialAccountId.isBlank()) throw new IllegalArgumentException("financial account id cannot be blank");
        values.put(currency, financialAccountId);
        return this;
    }
    public PayoutDestinations remove(String currency) { values.remove(currency); return this; }
    public String get(String currency) { return values.get(currency); }
    @JsonValue public Map<String, String> values() { return Collections.unmodifiableMap(new LinkedHashMap<>(values)); }
}
