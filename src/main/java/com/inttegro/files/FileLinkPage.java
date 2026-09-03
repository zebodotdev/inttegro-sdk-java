package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class FileLinkPage {
    public Integer number;
    public Integer size;
    @JsonProperty("file_links") public List<FileLink> fileLinks;
}
