package com.inttegro.refunds;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum RefundStatus {
    @JsonProperty("canceled") CANCELED,
    @JsonProperty("failed") FAILED,
    @JsonProperty("pending") PENDING,
    @JsonProperty("processing") PROCESSING,
    @JsonProperty("succeeded") SUCCEEDED
}
