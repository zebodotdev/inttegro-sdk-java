package com.inttegro;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

/** A deliberately extensible JSON value whose type is defined by an integration. */
public final class JsonValue {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final JsonNode value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public JsonValue(JsonNode value) {
        this.value = value == null ? NullNode.getInstance() : value.deepCopy();
    }

    public static JsonValue of(Object value) {
        return new JsonValue(JSON.valueToTree(value));
    }

    public <T> T as(Class<T> type) {
        return JSON.convertValue(value, type);
    }

    @com.fasterxml.jackson.annotation.JsonValue
    public JsonNode value() {
        return value.deepCopy();
    }
}
