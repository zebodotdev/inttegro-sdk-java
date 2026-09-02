package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PageSecretKeysParams {
    public Integer page;
    public Integer number;
    public Integer size;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PageSecretKeysParams params = new PageSecretKeysParams();
        public Builder page(Integer page) { params.page = page; return this; }
        public Builder number(Integer number) { params.number = number; return this; }
        public Builder size(Integer size) { params.size = size; return this; }
        public PageSecretKeysParams build() { return params; }
    }
}
