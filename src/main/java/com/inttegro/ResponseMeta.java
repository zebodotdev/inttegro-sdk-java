package com.inttegro;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Response-only metadata returned by the Inttegro API outside the decoded resource. */
public final class ResponseMeta {
    private static final ResponseMeta EMPTY = new ResponseMeta(Map.of());

    private final Map<String, Object> values;

    private ResponseMeta(Map<String, Object> values) {
        this.values = values == null ? Map.of() : Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }

    static ResponseMeta from(Map<String, Object> values) {
        if (values == null || values.isEmpty()) {
            return empty();
        }
        return new ResponseMeta(values);
    }

    public static ResponseMeta empty() {
        return EMPTY;
    }

    public Object get(String key) {
        return values.get(key);
    }

    public String getString(String key) {
        Object value = get(key);
        return value == null ? null : value.toString();
    }

    public boolean contains(String key) {
        return values.containsKey(key);
    }

    public int size() {
        return values.size();
    }
}
