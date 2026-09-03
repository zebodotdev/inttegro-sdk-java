package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileLinkDelivery {
    @JsonProperty("content_type")
    public String contentType;
    public String disposition;
    public String filename;
    public FileLinkDeliveryMode mode;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final FileLinkDelivery delivery = new FileLinkDelivery();
        public Builder contentType(String contentType) { delivery.contentType = contentType; return this; }
        public Builder disposition(String disposition) { delivery.disposition = disposition; return this; }
        public Builder filename(String filename) { delivery.filename = filename; return this; }
        public Builder mode(FileLinkDeliveryMode mode) { delivery.mode = mode; return this; }
        public FileLinkDelivery build() { return delivery; }
    }
}
