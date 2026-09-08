package com.inttegro.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Message headers with explicit, controlled mutation. */
public final class MessageHeaders {
    private final Map<String, String> values = new LinkedHashMap<>();

    public MessageHeaders() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public MessageHeaders(Map<String, String> values) {
        if (values != null) values.forEach(this::set);
    }

    public MessageHeaders set(String name, String value) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("header name cannot be blank");
        values.put(name, Objects.requireNonNull(value, "header value cannot be null"));
        return this;
    }

    public String remove(String name) { return values.remove(name); }
    public String get(String name) { return values.get(name); }
    public int size() { return values.size(); }

    @JsonValue
    public Map<String, String> values() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }
}
