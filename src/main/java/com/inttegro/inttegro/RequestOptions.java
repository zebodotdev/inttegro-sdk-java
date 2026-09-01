package com.inttegro.inttegro;

public record RequestOptions(String idempotencyKey) {
    public static RequestOptions withIdempotencyKey(String idempotencyKey) {
        return new RequestOptions(idempotencyKey);
    }
}
