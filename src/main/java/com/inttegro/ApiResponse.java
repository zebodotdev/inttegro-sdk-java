package com.inttegro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Decoded SDK value plus response-only HTTP metadata.
 */
public final class ApiResponse<T> {
    private final T data;
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final Map<String, Object> meta;

    public ApiResponse(T data, int statusCode, Map<String, List<String>> headers, Map<String, Object> meta) {
        this.data = data;
        this.statusCode = statusCode;
        this.headers = headers == null ? Map.of() : Map.copyOf(headers);
        this.meta = meta == null ? null : Map.copyOf(meta);
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public String getRequestId() {
        return firstHeader("x-request-id").orElse(null);
    }

    public String getRetryAfter() {
        return firstHeader("retry-after").orElse(null);
    }

    private Optional<String> firstHeader(String name) {
        return headers.entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(name))
                .flatMap(entry -> entry.getValue().stream())
                .findFirst();
    }
}
