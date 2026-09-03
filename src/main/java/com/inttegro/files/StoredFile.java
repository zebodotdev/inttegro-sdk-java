package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public final class StoredFile {
    public String id;
    public String purpose;
    public FileStatus status;
    @JsonProperty("scan_status") public FileScanStatus scanStatus;
    public String name;
    public String filename;
    @JsonProperty("content_type") public String contentType;
    public Long size;
    public String title;
    @JsonProperty("custom_data") public Map<String, String> customData;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("updated_at") public String updatedAt;
    @JsonProperty("deleted_at") public String deletedAt;
}
