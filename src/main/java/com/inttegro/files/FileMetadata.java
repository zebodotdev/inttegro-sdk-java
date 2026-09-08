package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Server-derived string metadata for a stored file or upload request. */
public final class FileMetadata {
    private final Map<String, String> values;
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public FileMetadata(Map<String, String> values) {
        this.values = values == null ? Map.of() : new LinkedHashMap<>(values);
    }
    public String get(String key) { return values.get(key); }
    public int size() { return values.size(); }
    @JsonValue public Map<String, String> values() { return Collections.unmodifiableMap(values); }
}
