package com.inttegro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/** Merge operations for custom data. A null value explicitly removes a key. */
public final class CustomDataPatch {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final Map<String, JsonNode> changes = new LinkedHashMap<>();

    public CustomDataPatch() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CustomDataPatch(Map<String, JsonNode> changes) {
        if (changes != null) {
            changes.forEach((key, value) -> {
                if (value == null || value.isNull()) unset(key); else setNode(key, value);
            });
        }
    }

    public CustomDataPatch set(String key, Object value) {
        Objects.requireNonNull(value, "custom data patch value cannot be null; use unset instead");
        return setNode(key, JSON.valueToTree(value));
    }

    private CustomDataPatch setNode(String key, JsonNode value) {
        CustomData.requireKey(key);
        JsonNode previous = changes.put(key, value.deepCopy());
        try {
            CustomData.requireSize(changes);
        } catch (RuntimeException exception) {
            if (previous == null) changes.remove(key); else changes.put(key, previous);
            throw exception;
        }
        return this;
    }

    public CustomDataPatch unset(String key) {
        CustomData.requireKey(key);
        boolean existed = changes.containsKey(key);
        JsonNode previous = changes.put(key, NullNode.getInstance());
        try {
            CustomData.requireSize(changes);
        } catch (RuntimeException exception) {
            if (existed) changes.put(key, previous); else changes.remove(key);
            throw exception;
        }
        return this;
    }

    public CustomDataPatch removeChange(String key) {
        changes.remove(key);
        return this;
    }

    @JsonValue
    public Map<String, JsonNode> changes() {
        Map<String, JsonNode> copy = new LinkedHashMap<>();
        changes.forEach((key, value) -> copy.put(key, value.deepCopy()));
        return Collections.unmodifiableMap(copy);
    }
}
