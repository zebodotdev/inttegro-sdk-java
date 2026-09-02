package com.inttegro.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReviewUploadRequestAttemptByOrdinalParams {
    @JsonProperty("attempt_ordinal")
    public Long attemptOrdinal;
    public UploadRequestReviewDecision decision;
    public String id;
    @JsonProperty("public_message")
    public String publicMessage;
    public List<UploadRequestReviewReason> reasons;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ReviewUploadRequestAttemptByOrdinalParams params = new ReviewUploadRequestAttemptByOrdinalParams();
        public Builder attemptOrdinal(Long attemptOrdinal) { params.attemptOrdinal = attemptOrdinal; return this; }
        public Builder decision(UploadRequestReviewDecision decision) { params.decision = decision; return this; }
        public Builder id(String id) { params.id = id; return this; }
        public Builder publicMessage(String publicMessage) { params.publicMessage = publicMessage; return this; }
        public Builder reasons(List<UploadRequestReviewReason> reasons) { params.reasons = reasons; return this; }
        public ReviewUploadRequestAttemptByOrdinalParams build() { return params; }
    }
}
