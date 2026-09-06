package com.inttegro;

import com.inttegro.diagnostics.ErrorReport;
import com.inttegro.diagnostics.ErrorReporter;
import com.inttegro.diagnostics.ErrorReportingPolicy;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapSetter;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

final class Telemetry {
    private static final Set<String> SAFE_RESOURCES = Set.of(
            "apps", "balance_transactions", "balances", "broadcasts", "checkout", "chimes", "customers",
            "file_links", "file_references", "files", "financial_accounts", "keys", "message_templates",
            "orders", "otp", "payment_methods", "payouts", "ping", "prices", "products", "purchase_intents",
            "refunds", "schedules", "sessions", "spec", "upload_requests"
    );
    private static final Set<String> SAFE_ACTIONS = Set.of(
            "activate", "add_price", "archive", "broadcast", "cancel", "complete", "confirm_payment",
            "confirm_verification", "connect", "contents", "countries", "create", "deactivate", "delete",
            "destroy", "disable", "disable_fx", "disable_pull", "disable_push", "disactivate", "disconnect",
            "enable", "enable_fx", "enable_pull", "enable_push", "finalize", "generate", "initiate", "lookup",
            "new", "open", "page", "pay", "publish", "reconcile", "reconnect", "refund", "render_preview",
            "request_confirmation", "review", "revoke", "schedule", "send", "send_invoice", "send_receipt",
            "set_default_unit_price", "set_destinations", "settings", "tokenize", "unarchive", "unpublish",
            "update", "upload", "usage", "verify"
    );
    private static final TextMapSetter<HttpRequest.Builder> HEADER_SETTER =
            (carrier, key, value) -> {
                if (carrier.build().headers().firstValue(key).isEmpty()) {
                    carrier.header(key, value);
                }
            };

    private final OpenTelemetry openTelemetry;
    private final Tracer tracer;
    private final String baseUrl;
    private final boolean enabled;
    private final ErrorReporter errorReporter;
    private final ErrorReportingPolicy errorReportingPolicy;

    Telemetry(OpenTelemetry openTelemetry, String baseUrl, boolean enabled) {
        this(openTelemetry, baseUrl, enabled, null, ErrorReportingPolicy.UNEXPECTED);
    }

    Telemetry(
            OpenTelemetry openTelemetry,
            String baseUrl,
            boolean enabled,
            ErrorReporter errorReporter,
            ErrorReportingPolicy errorReportingPolicy
    ) {
        this.openTelemetry = openTelemetry;
        this.tracer = openTelemetry.getTracer("inttegro", Client.VERSION);
        this.baseUrl = baseUrl;
        this.enabled = enabled;
        this.errorReporter = errorReporter;
        this.errorReportingPolicy = errorReportingPolicy != null ? errorReportingPolicy : ErrorReportingPolicy.UNEXPECTED;
    }

    Request start(String method, String pathOrUrl, String explicitOperation) {
        if (!enabled && errorReporter == null) {
            return Request.disabled();
        }
        RequestDetails details = RequestDetails.from(baseUrl, pathOrUrl, explicitOperation);
        if (!enabled) {
            return new Request(null, null, null, errorReporter, errorReportingPolicy, details, method);
        }
        Span span = tracer.spanBuilder("inttegro." + details.operation())
                .setSpanKind(SpanKind.CLIENT)
                .setAttribute("inttegro.operation.name", details.operation())
                .setAttribute("inttegro.sdk.language", "java")
                .setAttribute("inttegro.sdk.version", Client.VERSION)
                .setAttribute("http.request.method", method.toUpperCase(Locale.ROOT))
                .setAttribute("server.address", details.serverAddress())
                .startSpan();
        if (details.route() != null) {
            span.setAttribute("url.template", details.route());
        }
        span.addEvent("inttegro.request.prepared");
        if (errorReporter == null) {
            return new Request(openTelemetry, span, span.makeCurrent(), null, errorReportingPolicy, null, null);
        }
        return new Request(openTelemetry, span, span.makeCurrent(), errorReporter, errorReportingPolicy, details, method);
    }

    static final class Request implements AutoCloseable {
        private final OpenTelemetry openTelemetry;
        private final Span span;
        private final Scope scope;
        private final ErrorReporter errorReporter;
        private final ErrorReportingPolicy errorReportingPolicy;
        private final RequestDetails details;
        private final String method;
        private final long startedAtNanos;

        private Request(
                OpenTelemetry openTelemetry,
                Span span,
                Scope scope,
                ErrorReporter errorReporter,
                ErrorReportingPolicy errorReportingPolicy,
                RequestDetails details,
                String method
        ) {
            this.openTelemetry = openTelemetry;
            this.span = span;
            this.scope = scope;
            this.errorReporter = errorReporter;
            this.errorReportingPolicy = errorReportingPolicy;
            this.details = details;
            this.method = method;
            this.startedAtNanos = errorReporter == null ? 0L : System.nanoTime();
        }

        private static Request disabled() {
            return new Request(null, null, null, null, ErrorReportingPolicy.UNEXPECTED, null, null);
        }

        void inject(HttpRequest.Builder builder) {
            if (span == null) {
                return;
            }
            openTelemetry.getPropagators().getTextMapPropagator().inject(
                    io.opentelemetry.context.Context.current(),
                    builder,
                    HEADER_SETTER
            );
        }

        void attempt() {
            if (span != null) {
                span.addEvent(
                        "inttegro.http.attempt.started",
                        Attributes.of(AttributeKey.longKey("http.request.resend_count"), 0L)
                );
            }
        }

        void response(HttpResponse<?> response) {
            if (span == null) {
                return;
            }
            span.setAttribute("http.response.status_code", response.statusCode());
            response.headers().firstValue("x-request-id")
                    .ifPresent(requestId -> span.setAttribute("inttegro.request.id", requestId));
            span.addEvent("inttegro.response.received");
        }

        void decoded() {
            if (span != null) {
                span.addEvent("inttegro.response.decoded");
            }
        }

        void fail(String errorType) {
            if (span == null || errorType == null || errorType.isEmpty() || "canceled".equals(errorType)) {
                return;
            }
            span.setAttribute("error.type", errorType);
            span.setStatus(StatusCode.ERROR, errorType);
            span.addEvent(
                    "inttegro.request.failed",
                    Attributes.of(AttributeKey.stringKey("error.type"), errorType)
            );
        }

        void fail(Throwable error, String errorType) {
            fail(errorType);
            if (errorReporter == null || "canceled".equals(errorType)) {
                return;
            }
            try {
                ApiException apiException = error instanceof ApiException exception ? exception : null;
                if (errorReportingPolicy == ErrorReportingPolicy.UNEXPECTED
                        && apiException != null
                        && apiException.getStatusCode() < 500
                        && !"unknown_error".equals(apiException.getType())) {
                    return;
                }

                ErrorReport.ApiErrorContext apiContext = null;
                Integer statusCode = null;
                String requestId = null;
                if (apiException != null) {
                    statusCode = apiException.getStatusCode();
                    requestId = apiException.getRequestId();
                    if (apiException.getType() != null || apiException.getCode() != null || apiException.getFixCode() != null) {
                        apiContext = new ErrorReport.ApiErrorContext(
                                apiException.getType(),
                                apiException.getCode(),
                                apiException.getFixCode()
                        );
                    }
                }
                ErrorReport.TraceContext traceContext = null;
                if (span != null && span.getSpanContext().isValid()) {
                    traceContext = new ErrorReport.TraceContext(
                            span.getSpanContext().getTraceId(),
                            span.getSpanContext().getSpanId()
                    );
                }
                long durationMs = Math.max(0L, (System.nanoTime() - startedAtNanos) / 1_000_000L);
                ErrorReport report = new ErrorReport(
                        1,
                        UUID.randomUUID().toString(),
                        java.time.Instant.now().toString(),
                        "error",
                        errorType,
                        details.operation(),
                        new ErrorReport.SdkContext("java", Client.VERSION),
                        new ErrorReport.HttpContext(
                                method.toUpperCase(Locale.ROOT),
                                details.route(),
                                details.serverAddress(),
                                statusCode,
                                requestId,
                                durationMs
                        ),
                        apiContext,
                        traceContext,
                        error.getClass().getSimpleName(),
                        String.join(":",
                                "inttegro",
                                "java",
                                details.operation(),
                                errorType,
                                statusCode != null ? statusCode.toString() : "none")
                );
                if (apiException != null) {
                    apiException.attachReport(report);
                }
                errorReporter.report(report);
            } catch (Throwable ignored) {
                // Report preparation and delivery must never replace the original failure.
            }
        }

        @Override
        public void close() {
            if (scope != null) {
                scope.close();
            }
            if (span != null) {
                span.end();
            }
        }
    }

    private record RequestDetails(String operation, String route, String serverAddress) {
        static RequestDetails from(String baseUrl, String pathOrUrl, String explicitOperation) {
            boolean relativePath = pathOrUrl.startsWith("/");
            String route = relativePath && isKnownRoute(pathOrUrl) ? pathOrUrl : null;
            String operation = explicitOperation;
            if (operation == null || operation.isBlank()) {
                operation = route != null ? operationFromRoute(route) : "http.request";
            }
            URI uri = URI.create(relativePath ? baseUrl : pathOrUrl);
            return new RequestDetails(operation, route, uri.getHost() != null ? uri.getHost() : "");
        }

        private static boolean isKnownRoute(String route) {
            String[] parts = route.replaceFirst("^/+", "").split("/");
            return parts.length > 0
                    && parts.length <= 2
                    && SAFE_RESOURCES.contains(parts[0])
                    && (parts.length == 1 || SAFE_ACTIONS.contains(parts[1]));
        }

        private static String operationFromRoute(String route) {
            String[] parts = route.replaceFirst("^/+", "").split("/");
            if (parts.length == 0 || parts[0].isEmpty()) {
                return "http.request";
            }
            if (parts.length == 1) {
                return "balances".equals(parts[0]) ? "balances.lookup" : parts[0] + ".request";
            }
            return parts[0] + "." + parts[parts.length - 1];
        }
    }
}
