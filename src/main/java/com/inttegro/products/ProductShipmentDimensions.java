package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductShipmentDimensions {
    public Double length;
    public Double width;
    public Double height;
    public Double weight;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductShipmentDimensions dimensions = new ProductShipmentDimensions();
        public Builder length(Double length) { dimensions.length = length; return this; }
        public Builder length(double length) { dimensions.length = length; return this; }
        public Builder width(Double width) { dimensions.width = width; return this; }
        public Builder width(double width) { dimensions.width = width; return this; }
        public Builder height(Double height) { dimensions.height = height; return this; }
        public Builder height(double height) { dimensions.height = height; return this; }
        public Builder weight(Double weight) { dimensions.weight = weight; return this; }
        public Builder weight(double weight) { dimensions.weight = weight; return this; }
        public ProductShipmentDimensions build() { return dimensions; }
    }
}
