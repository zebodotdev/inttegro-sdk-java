package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileModels {
    public static class FileCreateParams {
        public String file;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        public String purpose;
        public String title;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileCreateParams params = new FileCreateParams();
            public Builder file(Path file) { params.file = file.toString(); return this; }
            public Builder file(String file) { params.file = file; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder purpose(String purpose) { params.purpose = purpose; return this; }
            public Builder title(String title) { params.title = title; return this; }
            public FileCreateParams build() { return params; }
        }
    }

    public static class FilePageParams {
        @JsonProperty("created_after")
        public String createdAfter;
        @JsonProperty("created_before")
        public String createdBefore;
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;
        public String purpose;
        public String status;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FilePageParams params = new FilePageParams();
            public Builder createdAfter(String createdAfter) { params.createdAfter = createdAfter; return this; }
            public Builder createdBefore(String createdBefore) { params.createdBefore = createdBefore; return this; }
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public Builder purpose(String purpose) { params.purpose = purpose; return this; }
            public Builder status(String status) { params.status = status; return this; }
            public FilePageParams build() { return params; }
        }
    }

    public static class FileContentsParams {
        public String disposition;
        @JsonProperty("file_id")
        public String fileId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileContentsParams params = new FileContentsParams();
            public Builder disposition(String disposition) { params.disposition = disposition; return this; }
            public Builder fileId(String fileId) { params.fileId = fileId; return this; }
            public FileContentsParams build() { return params; }
        }
    }

    public static class FileLinkAccess {
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

    public static class FileLinkDelivery {
        @JsonProperty("content_type")
        public String contentType;
        public String disposition;
        public String filename;
        public String mode;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileLinkDelivery delivery = new FileLinkDelivery();
            public Builder contentType(String contentType) { delivery.contentType = contentType; return this; }
            public Builder disposition(String disposition) { delivery.disposition = disposition; return this; }
            public Builder filename(String filename) { delivery.filename = filename; return this; }
            public Builder mode(String mode) { delivery.mode = mode; return this; }
            public FileLinkDelivery build() { return delivery; }
        }
    }

    public static class Actor {
        public String email;
        public String id;
        public String name;
        public String service;
        public String type;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final Actor actor = new Actor();
            public Builder email(String email) { actor.email = email; return this; }
            public Builder id(String id) { actor.id = id; return this; }
            public Builder name(String name) { actor.name = name; return this; }
            public Builder service(String service) { actor.service = service; return this; }
            public Builder type(String type) { actor.type = type; return this; }
            public Actor build() { return actor; }
        }
    }

    public static class ResourceRef {
        public String id;
        public String name;
        public String type;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ResourceRef resource = new ResourceRef();
            public Builder id(String id) { resource.id = id; return this; }
            public Builder name(String name) { resource.name = name; return this; }
            public Builder type(String type) { resource.type = type; return this; }
            public ResourceRef build() { return resource; }
        }
    }

    public static class FileLinkCreateParams {
        public FileLinkAccess access;
        @JsonProperty("created_by")
        public Actor createdBy;
        public FileLinkDelivery delivery;
        @JsonProperty("expires_at")
        public String expiresAt;
        @JsonProperty("file_id")
        public String fileId;
        @JsonProperty("custom_data")
        public Map<String, String> customData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileLinkCreateParams params = new FileLinkCreateParams();
            public Builder access(FileLinkAccess access) { params.access = access; return this; }
            public Builder createdBy(Actor createdBy) { params.createdBy = createdBy; return this; }
            public Builder delivery(FileLinkDelivery delivery) { params.delivery = delivery; return this; }
            public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
            public Builder fileId(String fileId) { params.fileId = fileId; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public FileLinkCreateParams build() { return params; }
        }
    }

    public static class FileLinkPageParams {
        @JsonProperty("file_id")
        public String fileId;
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;
        public String status;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileLinkPageParams params = new FileLinkPageParams();
            public Builder fileId(String fileId) { params.fileId = fileId; return this; }
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public Builder status(String status) { params.status = status; return this; }
            public FileLinkPageParams build() { return params; }
        }
    }

    public static class FileLinkRevokeParams {
        public String id;
        @JsonProperty("revoked_by")
        public Actor revokedBy;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FileLinkRevokeParams params = new FileLinkRevokeParams();
            public Builder id(String id) { params.id = id; return this; }
            public Builder revokedBy(Actor revokedBy) { params.revokedBy = revokedBy; return this; }
            public FileLinkRevokeParams build() { return params; }
        }
    }

    public static class UploadAttempts {
        @JsonProperty("max_attempts")
        public Integer maxAttempts;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadAttempts attempts = new UploadAttempts();
            public Builder maxAttempts(Integer maxAttempts) { attempts.maxAttempts = maxAttempts; return this; }
            public UploadAttempts build() { return attempts; }
        }
    }

    public static class UploadConstraints {
        @JsonProperty("content_types")
        public List<String> contentTypes;
        @JsonProperty("exact_size")
        public Integer exactSize;
        public List<String> extensions;
        public String filename;
        @JsonProperty("max_size")
        public Integer maxSize;
        @JsonProperty("min_size")
        public Integer minSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadConstraints constraints = new UploadConstraints();
            public Builder contentTypes(List<String> contentTypes) { constraints.contentTypes = contentTypes; return this; }
            public Builder exactSize(Integer exactSize) { constraints.exactSize = exactSize; return this; }
            public Builder extensions(List<String> extensions) { constraints.extensions = extensions; return this; }
            public Builder filename(String filename) { constraints.filename = filename; return this; }
            public Builder maxSize(Integer maxSize) { constraints.maxSize = maxSize; return this; }
            public Builder minSize(Integer minSize) { constraints.minSize = minSize; return this; }
            public UploadConstraints build() { return constraints; }
        }
    }

    public static class UploadDisplay {
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

    public static class UploadRequestCreateParams {
        public UploadAttempts attempts;
        public UploadConstraints constraints;
        public UploadDisplay display;
        @JsonProperty("expires_at")
        public String expiresAt;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        public String purpose;
        public Actor recipient;
        public Actor requester;
        public ResourceRef resource;
        public Actor subject;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadRequestCreateParams params = new UploadRequestCreateParams();
            public Builder attempts(UploadAttempts attempts) { params.attempts = attempts; return this; }
            public Builder constraints(UploadConstraints constraints) { params.constraints = constraints; return this; }
            public Builder display(UploadDisplay display) { params.display = display; return this; }
            public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder purpose(String purpose) { params.purpose = purpose; return this; }
            public Builder recipient(Actor recipient) { params.recipient = recipient; return this; }
            public Builder requester(Actor requester) { params.requester = requester; return this; }
            public Builder resource(ResourceRef resource) { params.resource = resource; return this; }
            public Builder subject(Actor subject) { params.subject = subject; return this; }
            public UploadRequestCreateParams build() { return params; }
        }
    }

    public static class UploadRequestPageParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;
        public String purpose;
        public ResourceRef resource;
        public String status;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadRequestPageParams params = new UploadRequestPageParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public Builder purpose(String purpose) { params.purpose = purpose; return this; }
            public Builder resource(ResourceRef resource) { params.resource = resource; return this; }
            public Builder status(String status) { params.status = status; return this; }
            public UploadRequestPageParams build() { return params; }
        }
    }

    public static class UploadRequestCancelParams {
        @JsonProperty("canceled_by")
        public Actor canceledBy;
        public String id;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadRequestCancelParams params = new UploadRequestCancelParams();
            public Builder canceledBy(Actor canceledBy) { params.canceledBy = canceledBy; return this; }
            public Builder id(String id) { params.id = id; return this; }
            public UploadRequestCancelParams build() { return params; }
        }
    }

    public static class UploadRequestFulfillParams {
        public String file;
        @JsonProperty("upload_url")
        public String uploadUrl;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UploadRequestFulfillParams params = new UploadRequestFulfillParams();
            public Builder file(Path file) { params.file = file.toString(); return this; }
            public Builder file(String file) { params.file = file; return this; }
            public Builder uploadUrl(String uploadUrl) { params.uploadUrl = uploadUrl; return this; }
            public UploadRequestFulfillParams build() { return params; }
        }
    }
}
