package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestMeta {
    @JsonProperty("idempotency_key")
    public String idempotencyKey;

    public static RequestMeta withIdempotencyKey(String key) {
        RequestMeta meta = new RequestMeta();
        meta.idempotencyKey = key;
        return meta;
    }
}
