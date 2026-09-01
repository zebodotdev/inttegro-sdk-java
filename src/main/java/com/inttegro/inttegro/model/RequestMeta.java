package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestMeta {
    @JsonProperty("idempotency_key")
    public String idempotencyKey;

    public static Builder builder() { return new Builder(); }

    public static RequestMeta withIdempotencyKey(String key) {
        RequestMeta meta = new RequestMeta();
        meta.idempotencyKey = key;
        return meta;
    }

    public static class Builder {
        private final RequestMeta meta = new RequestMeta();
        public Builder idempotencyKey(String idempotencyKey) { meta.idempotencyKey = idempotencyKey; return this; }
        public RequestMeta build() { return meta; }
    }
}
