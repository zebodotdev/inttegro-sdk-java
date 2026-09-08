package com.inttegro.specifications;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Country specifications keyed by ISO country code. */
public final class CountrySpecifications {
    private final Map<String, CountrySpecification> countries;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CountrySpecifications(Map<String, CountrySpecification> countries) {
        this.countries = countries == null ? Map.of() : new LinkedHashMap<>(countries);
    }

    public CountrySpecification get(String countryCode) { return countries.get(countryCode); }
    public int size() { return countries.size(); }
    @JsonValue public Map<String, CountrySpecification> values() { return Collections.unmodifiableMap(countries); }
}
