package com.inttegro;

import com.inttegro.diagnostics.ErrorReport;

/**
 * API exception wrapping HTTP status and error payload.
 */
public class ApiException extends Exception {
    private final int statusCode;
    private final String code;
    private final String type;
    private final String url;
    private final String detail;
    private final String fixCode;
    private final String errorCause;
    private final String requestId;
    private ErrorReport report;

    public ApiException(
            int statusCode,
            String code,
            String type,
            String url,
            String message,
            String detail,
            String fixCode,
            String cause
    ) {
        this(statusCode, code, type, url, message, detail, fixCode, cause, null);
    }

    public ApiException(
            int statusCode,
            String code,
            String type,
            String url,
            String message,
            String detail,
            String fixCode,
            String cause,
            String requestId
    ) {
        super(message != null ? message : detail != null ? detail : code);
        this.statusCode = statusCode;
        this.code = code;
        this.type = type;
        this.url = url;
        this.detail = detail;
        this.fixCode = fixCode;
        this.errorCause = cause;
        this.requestId = requestId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getDetail() {
        return detail;
    }

    public String getFixCode() {
        return fixCode;
    }

    public String getErrorCause() {
        return errorCause;
    }

    public String getRequestId() {
        return requestId;
    }

    /** Returns the generated report, or null when no reporter was configured or selected it. */
    public ErrorReport getReport() {
        return report;
    }

    void attachReport(ErrorReport report) {
        this.report = report;
    }
}
