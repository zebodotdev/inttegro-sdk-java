package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductCategory {
    public String id;
    public String name;
    public String slug;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductCategory category = new ProductCategory();
        public Builder id(String id) { category.id = id; return this; }
        public Builder name(String name) { category.name = name; return this; }
        public Builder slug(String slug) { category.slug = slug; return this; }
        public ProductCategory build() { return category; }
    }
}
