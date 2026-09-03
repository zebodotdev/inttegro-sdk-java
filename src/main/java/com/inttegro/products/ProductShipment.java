package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductShipment {
    public ProductShipmentType type;
    public String carrier;
    public ProductShipmentDimensions dimensions;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductShipment shipment = new ProductShipment();
        public Builder type(ProductShipmentType type) { shipment.type = type; return this; }
        public Builder carrier(String carrier) { shipment.carrier = carrier; return this; }
        public Builder dimensions(ProductShipmentDimensions dimensions) { shipment.dimensions = dimensions; return this; }
        public ProductShipment build() { return shipment; }
    }
}
