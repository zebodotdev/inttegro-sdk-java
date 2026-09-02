package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class UploadDisplay {
    public String description;
    @JsonProperty("help_text")
    public String helpText;
    public String title;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final UploadDisplay display = new UploadDisplay();
        public Builder description(String description) { display.description = description; return this; }
        public Builder helpText(String helpText) { display.helpText = helpText; return this; }
        public Builder title(String title) { display.title = title; return this; }
        public UploadDisplay build() { return display; }
    }
}
