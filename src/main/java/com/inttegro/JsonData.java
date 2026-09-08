package com.inttegro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Deliberately extensible JSON object for integration-defined data. */
public final class JsonData {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final Map<String, JsonNode> values = new LinkedHashMap<>();

    public JsonData() {}

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public JsonData(Map<String, JsonNode> values) {
        if (values != null) {
            values.forEach((key, value) -> this.values.put(key, value == null ? NullNode.getInstance() : value.deepCopy()));
        }
    }

    public JsonData set(String key, Object value) {
        values.put(key, JSON.valueToTree(value));
        return this;
    }

    public JsonData remove(String key) {
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
}
