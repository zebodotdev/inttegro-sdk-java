package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductShipmentType {
    @JsonProperty("delivery") DELIVERY,
    @JsonProperty("download") DOWNLOAD,
    @JsonProperty("render") RENDER,
    @JsonProperty("service") SERVICE,
    @JsonProperty("stream") STREAM
}
