package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductMediaItem {
    public String url;
    public String type;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductMediaItem media = new ProductMediaItem();
        public Builder url(String url) { media.url = url; return this; }
        public Builder type(String type) { media.type = type; return this; }
        public ProductMediaItem build() { return media; }
    }
}
