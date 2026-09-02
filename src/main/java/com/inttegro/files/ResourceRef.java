package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ResourceRef {
    public String id;
    public String name;
    public String type;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ResourceRef resource = new ResourceRef();
        public Builder id(String id) { resource.id = id; return this; }
        public Builder name(String name) { resource.name = name; return this; }
        public Builder type(String type) { resource.type = type; return this; }
        public ResourceRef build() { return resource; }
    }
}
