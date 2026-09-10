package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentError {
    public String message;
    @JsonProperty("docs_url") public String docsUrl;
    public String source;
    public String type;
    public String code;
}
