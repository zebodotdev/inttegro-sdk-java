package com.inttegro.apps;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class App {
    public String id;
    public String name;
    public String alias;
    public String description;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("updated_at") public OffsetDateTime updatedAt;
    @JsonProperty("archived_at") public OffsetDateTime archivedAt;
}
