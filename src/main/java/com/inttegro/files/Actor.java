package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Actor {
    public String email;
    public String id;
    public String name;
    public String service;
    public String type;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Actor actor = new Actor();
        public Builder email(String email) { actor.email = email; return this; }
        public Builder id(String id) { actor.id = id; return this; }
        public Builder name(String name) { actor.name = name; return this; }
        public Builder service(String service) { actor.service = service; return this; }
        public Builder type(String type) { actor.type = type; return this; }
        public Actor build() { return actor; }
    }
}
