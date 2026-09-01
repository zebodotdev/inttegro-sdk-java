package com.inttegro.inttegro;

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
        super(message != null ? message : detail != null ? detail : code);
        this.statusCode = statusCode;
        this.code = code;
        this.type = type;
        this.url = url;
        this.detail = detail;
        this.fixCode = fixCode;
        this.errorCause = cause;
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
}
