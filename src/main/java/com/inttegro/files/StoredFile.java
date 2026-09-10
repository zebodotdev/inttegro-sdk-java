package com.inttegro.files;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

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
    @JsonProperty("custom_data") public CustomData customData;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("updated_at") public OffsetDateTime updatedAt;
    @JsonProperty("deleted_at") public OffsetDateTime deletedAt;
}
