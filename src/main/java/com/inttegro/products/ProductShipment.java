package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.JsonData;
import java.util.List;
import java.util.Map;

public class ProductShipment {
    public ProductShipmentType type;
    public JsonData delivery;
    public JsonData download;
    public JsonData render;
    public JsonData service;
    public JsonData stream;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductShipment shipment = new ProductShipment();
        public Builder type(ProductShipmentType type) { shipment.type = type; return this; }
        public Builder delivery(JsonData delivery) { shipment.delivery = delivery; return this; }
        public Builder download(JsonData download) { shipment.download = download; return this; }
        public Builder render(JsonData render) { shipment.render = render; return this; }
        public Builder service(JsonData service) { shipment.service = service; return this; }
        public Builder stream(JsonData stream) { shipment.stream = stream; return this; }
        public ProductShipment build() { return shipment; }
    }
}
