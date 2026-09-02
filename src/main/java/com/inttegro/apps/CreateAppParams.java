package com.inttegro.apps;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateAppParams {
    public String name;
    public String alias;
    public String description;
    @JsonProperty("legal_entity_type") public String legalEntityType;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final CreateAppParams params = new CreateAppParams();
        public Builder name(String name) { params.name = name; return this; }
        public Builder alias(String alias) { params.alias = alias; return this; }
        public Builder description(String description) { params.description = description; return this; }
        public Builder legalEntityType(String legalEntityType) {
            params.legalEntityType = legalEntityType;
            return this;
        }
        public CreateAppParams build() { return params; }
    }
}
