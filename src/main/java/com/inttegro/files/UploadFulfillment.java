package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class UploadFulfillment {
    @JsonProperty("upload_request") public UploadRequest uploadRequest;
    public StoredFile file;
}
