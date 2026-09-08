package com.inttegro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Merchant-defined input values that Inttegro serializes to stored strings. */
public final class CustomDataInput {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final Map<String, JsonNode> values = new LinkedHashMap<>();

    public CustomDataInput() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public CustomDataInput(Map<String, JsonNode> values) {
        if (values != null) {
            values.forEach((key, value) -> setNode(key, value));
        }
    }

    public CustomDataInput set(String key, Object value) {
        return setNode(key, JSON.valueToTree(value));
    }

    public CustomDataInput remove(String key) {
        values.remove(key);
        return this;
    }

    public JsonNode get(String key) {
        JsonNode value = values.get(key);
        return value == null ? null : value.deepCopy();
    }

    @JsonValue
    public Map<String, JsonNode> values() {
        Map<String, JsonNode> copy = new LinkedHashMap<>();
        values.forEach((key, value) -> copy.put(key, value.deepCopy()));
        return Collections.unmodifiableMap(copy);
    }

    private CustomDataInput setNode(String key, JsonNode value) {
        CustomData.requireKey(key);
        JsonNode normalized = value == null ? NullNode.getInstance() : value.deepCopy();
        JsonNode previous = values.put(key, normalized);
        try {
            CustomData.requireSize(values);
        } catch (RuntimeException exception) {
            if (previous == null) values.remove(key); else values.put(key, previous);
            throw exception;
        }
        return this;
    }
}
