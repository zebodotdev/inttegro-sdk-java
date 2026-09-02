package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileLinkAccess {
    @JsonProperty("allow_download")
    public Boolean allowDownload;
    @JsonProperty("allowed_ip_ranges")
    public List<String> allowedIpRanges;
    @JsonProperty("allowed_origins")
    public List<String> allowedOrigins;
    @JsonProperty("max_accesses")
    public Integer maxAccesses;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileLinkAccess access = new FileLinkAccess();
        public Builder allowDownload(Boolean allowDownload) { access.allowDownload = allowDownload; return this; }
        public Builder allowedIpRanges(List<String> allowedIpRanges) { access.allowedIpRanges = allowedIpRanges; return this; }
        public Builder allowedOrigins(List<String> allowedOrigins) { access.allowedOrigins = allowedOrigins; return this; }
        public Builder maxAccesses(Integer maxAccesses) { access.maxAccesses = maxAccesses; return this; }
        public FileLinkAccess build() { return access; }
    }
}
