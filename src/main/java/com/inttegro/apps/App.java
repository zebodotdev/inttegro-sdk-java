package com.inttegro.apps;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class App {
    public String id;
    public String name;
    public String alias;
    public String description;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("updated_at") public String updatedAt;
    @JsonProperty("archived_at") public String archivedAt;
}
