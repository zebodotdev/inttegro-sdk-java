package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Models for application management endpoints. */
public class AppsModels {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreateAppParams {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UpdateAppParams {
        public String name;
        public String alias;
        public String description;
        @JsonProperty("legal_entity_type") public String legalEntityType;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdateAppParams params = new UpdateAppParams();
            public Builder name(String name) { params.name = name; return this; }
            public Builder alias(String alias) { params.alias = alias; return this; }
            public Builder description(String description) { params.description = description; return this; }
            public Builder legalEntityType(String legalEntityType) {
                params.legalEntityType = legalEntityType;
                return this;
            }
            public UpdateAppParams build() { return params; }
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LookupAppParams {
        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupAppParams params = new LookupAppParams();
            public LookupAppParams build() { return params; }
        }
    }

    public static class App {
        public String id;
        public String name;
        public String alias;
        public String description;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("updated_at") public String updatedAt;
        @JsonProperty("archived_at") public String archivedAt;
    }

    public static class CreateAppResponse { public App app; }
    public static class LookupAppResponse { public App app; }
    public static class UpdateAppResponse { public App app; }
}
