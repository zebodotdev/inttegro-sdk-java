package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Physical, digital, or merchant-defined product dimensions. */
public final class ProductDimensions {
    public Physical physical;
    public Digital digital;
    public Custom custom;

    public static final class Physical {
        @JsonProperty("weight_unit") public String weightUnit;
        public Double weight;
        public Double size;
        @JsonProperty("volume_unit") public String volumeUnit;
        public Double volume;
        public Double length;
        public Double height;
        public Double width;
    }

    public static final class Digital {
        public Double bytes;
        @JsonProperty("size_unit") public String sizeUnit;
        public Double size;
    }

    public static final class Custom {
        @JsonProperty("size_unit") public String sizeUnit;
        public Double size;
        public ProductDimensionDetails details;
    }
}
