package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class GhanaBankAccount {
    @JsonProperty("bank_name") public String bankName;
    public String branch;
    public String number;
    @JsonProperty("sort_code") public String sortCode;
    @JsonProperty("swift_code") public String swiftCode;
    @JsonProperty("holder") public BankAccountOwner holder;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final GhanaBankAccount account = new GhanaBankAccount();
        public Builder bankName(String bankName) { account.bankName = bankName; return this; }
        public Builder branch(String branch) { account.branch = branch; return this; }
        public Builder number(String number) { account.number = number; return this; }
        public Builder sortCode(String sortCode) { account.sortCode = sortCode; return this; }
        public Builder swiftCode(String swiftCode) { account.swiftCode = swiftCode; return this; }
        public Builder holder(BankAccountOwner holder) { account.holder = holder; return this; }
        public GhanaBankAccount build() { return account; }
    }
}
