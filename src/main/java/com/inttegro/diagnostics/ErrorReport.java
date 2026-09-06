package com.inttegro.diagnostics;

/** A privacy-safe description of a failed Inttegro SDK operation. */
public record ErrorReport(
        int schemaVersion,
        String eventId,
        String occurredAt,
        String severity,
        String category,
        String operation,
        SdkContext sdk,
        HttpContext http,
        ApiErrorContext apiError,
        TraceContext trace,
        String exceptionType,
        String fingerprint
) {
    public record SdkContext(String language, String version) {}

    public record HttpContext(
            String method,
            String route,
            String serverAddress,
            Integer statusCode,
            String requestId,
            long durationMs
    ) {}

    public record ApiErrorContext(String type, String code, String fixCode) {}

    public record TraceContext(String traceId, String spanId) {}
}
