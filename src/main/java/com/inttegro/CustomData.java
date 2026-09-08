package com.inttegro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Merchant-defined string values attached to an Inttegro resource. */
public final class CustomData {
    public static final int MAX_KEY_BYTES = 256;
    public static final int MAX_ENCODED_BYTES = 25 * 1024;

    private static final ObjectMapper JSON = new ObjectMapper();
    private final Map<String, String> values = new LinkedHashMap<>();

    public CustomData() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CustomData(Map<String, String> values) {
        if (values != null) {
            values.forEach(this::set);
        }
    }

    public static CustomData of(Map<String, String> values) {
        return new CustomData(values);
    }

    public CustomData set(String key, String value) {
        requireKey(key);
        Objects.requireNonNull(value, "custom data value cannot be null");
        String previous = values.put(key, value);
        try {
            requireSize(values);
        } catch (RuntimeException exception) {
            if (previous == null) values.remove(key); else values.put(key, previous);
            throw exception;
        }
        return this;
    }

    public CustomData remove(String key) {
        values.remove(key);
        return this;
    }

    public String get(String key) { return values.get(key); }
    public boolean contains(String key) { return values.containsKey(key); }
    public int size() { return values.size(); }

    @JsonValue
    public Map<String, String> values() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(values));
    }

    static void requireKey(String key) {
        if (key == null) throw new IllegalArgumentException("custom data key cannot be null");
        if (key.getBytes(StandardCharsets.UTF_8).length > MAX_KEY_BYTES) {
            throw new IllegalArgumentException("custom data key exceeds 256 bytes");
        }
    }

    static void requireSize(Map<?, ?> values) {
        try {
            if (JSON.writeValueAsBytes(values).length > MAX_ENCODED_BYTES) {
                throw new IllegalArgumentException("custom data exceeds 25 KiB");
            }
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("custom data is not JSON serializable", exception);
        }
    }
}
