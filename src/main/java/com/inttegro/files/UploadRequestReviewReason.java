package com.inttegro.files;

public class UploadRequestReviewReason {
    public String code;
    public String message;
    public String param;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadRequestReviewReason reason = new UploadRequestReviewReason();
        public Builder code(String code) { reason.code = code; return this; }
        public Builder message(String message) { reason.message = message; return this; }
        public Builder param(String param) { reason.param = param; return this; }
        public UploadRequestReviewReason build() { return reason; }
    }
}
