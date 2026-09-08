package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Named product-dimension details with controlled mutation. */
public final class ProductDimensionDetails {
    private final Map<String, String> values = new LinkedHashMap<>();

    public ProductDimensionDetails() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public ProductDimensionDetails(Map<String, String> values) {
        if (values != null) this.values.putAll(values);
    }

    public ProductDimensionDetails set(String name, String value) {
        values.put(name, value);
        return this;
    }

    public ProductDimensionDetails remove(String name) {
        values.remove(name);
        return this;
    }

    public String get(String name) { return values.get(name); }

    @JsonValue
    public Map<String, String> values() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }
}
