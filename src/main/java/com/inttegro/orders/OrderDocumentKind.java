package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderDocumentKind {
    @JsonProperty("invoice") INVOICE,
    @JsonProperty("receipt") RECEIPT
}
