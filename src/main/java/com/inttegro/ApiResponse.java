package com.inttegro;

import java.util.List;
import java.util.Map;

/**
 * Decoded SDK value plus response-only HTTP metadata.
 */
public final class ApiResponse<T> {
    private final T data;
    private final int statusCode;
    private final ResponseHeaders headers;
    private final ResponseMeta meta;

    ApiResponse(T data, int statusCode, Map<String, List<String>> headers, Map<String, Object> meta) {
        this(data, statusCode, ResponseHeaders.from(headers), ResponseMeta.from(meta));
    }

    public ApiResponse(T data, int statusCode, ResponseHeaders headers, ResponseMeta meta) {
        this.data = data;
        this.statusCode = statusCode;
        this.headers = headers == null ? ResponseHeaders.empty() : headers;
        this.meta = meta == null ? ResponseMeta.empty() : meta;
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ResponseHeaders getHeaders() {
        return headers;
    }

    public ResponseMeta getMeta() {
        return meta;
    }

    public String getRequestId() {
        return headers.first("x-request-id");
    }

    public String getRetryAfter() {
        return headers.first("retry-after");
    }
}
