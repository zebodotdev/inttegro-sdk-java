package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class UploadRequestPage {
    public Integer number;
    public Integer size;
    @JsonProperty("upload_requests") public List<UploadRequest> uploadRequests;
}
