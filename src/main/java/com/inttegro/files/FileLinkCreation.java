package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class FileLinkCreation {
    @JsonProperty("file_link") public FileLink fileLink;
    public String url;
}
