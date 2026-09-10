package com.inttegro.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentMethodSnapshotGhanaBankAccount {
    @JsonProperty("account_number") public String accountNumber;
    public String branch;
    public String name;
    @JsonProperty("sort_code") public String sortCode;
    @JsonProperty("swift_code") public String swiftCode;
}
