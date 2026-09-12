package com.inttegro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** HTTP response headers captured with a decoded SDK response. */
public final class ResponseHeaders {
    private static final ResponseHeaders EMPTY = new ResponseHeaders(Map.of());

    private final Map<String, List<String>> values;

    private ResponseHeaders(Map<String, List<String>> values) {
        Map<String, List<String>> copy = new LinkedHashMap<>();
        if (values != null) {
            values.forEach((key, headerValues) -> copy.put(
                    key,
                    headerValues == null ? List.of() : List.copyOf(headerValues)
            ));
        }
        this.values = Collections.unmodifiableMap(copy);
    }

    static ResponseHeaders from(Map<String, List<String>> values) {
        if (values == null || values.isEmpty()) {
            return empty();
        }
        return new ResponseHeaders(values);
    }

    public static ResponseHeaders empty() {
        return EMPTY;
    }

    public List<String> values(String name) {
        if (name == null) {
            return List.of();
        }
        List<String> matches = new ArrayList<>();
        values.forEach((key, headerValues) -> {
            if (key.equalsIgnoreCase(name)) {
                matches.addAll(headerValues);
            }
        });
        return List.copyOf(matches);
    }

    public String first(String name) {
        return values(name).stream().findFirst().orElse(null);
    }

    public boolean contains(String name) {
        return first(name) != null;
    }

    public int size() {
        return values.size();
    }
}
