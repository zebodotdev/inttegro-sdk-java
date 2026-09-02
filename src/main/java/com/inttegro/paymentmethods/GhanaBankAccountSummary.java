package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class GhanaBankAccountSummary {
    public String branch;
    public String name;
    @JsonProperty("account_number")
    public String accountNumber;
    @JsonProperty("sort_code")
    public String sortCode;
    @JsonProperty("swift_code")
    public String swiftCode;
}
