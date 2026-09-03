package com.inttegro.otp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.RequestMeta;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class InitiateOtpParams {
    @JsonProperty("request_meta") public RequestMeta requestMeta;
    @JsonProperty("async_delivery") public Boolean asyncDelivery;
    @JsonProperty("message_template") public String messageTemplate;
    public String purpose;
    public String recipient;
    public String sender;
    @JsonProperty("service_name") public String serviceName;
    @JsonProperty("token_alphabet") public String tokenAlphabet;
    @JsonProperty("token_alphabet_type") public OtpAlphabetType tokenAlphabetType;
    @JsonProperty("token_size") public Integer tokenSize;
    @JsonProperty("validity_duration_in_minutes") public Integer validityDurationInMinutes;

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private final InitiateOtpParams params = new InitiateOtpParams();

        public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
        public Builder asyncDelivery(boolean asyncDelivery) { params.asyncDelivery = asyncDelivery; return this; }
        public Builder messageTemplate(String messageTemplate) { params.messageTemplate = messageTemplate; return this; }
        public Builder purpose(String purpose) { params.purpose = purpose; return this; }
        public Builder recipient(String recipient) { params.recipient = recipient; return this; }
        public Builder sender(String sender) { params.sender = sender; return this; }
        public Builder serviceName(String serviceName) { params.serviceName = serviceName; return this; }
        public Builder tokenAlphabet(String tokenAlphabet) { params.tokenAlphabet = tokenAlphabet; return this; }
        public Builder tokenAlphabetType(OtpAlphabetType tokenAlphabetType) { params.tokenAlphabetType = tokenAlphabetType; return this; }
        public Builder tokenSize(int tokenSize) { params.tokenSize = tokenSize; return this; }
        public Builder validityDurationInMinutes(int minutes) { params.validityDurationInMinutes = minutes; return this; }
        public InitiateOtpParams build() { return params; }
    }
}
