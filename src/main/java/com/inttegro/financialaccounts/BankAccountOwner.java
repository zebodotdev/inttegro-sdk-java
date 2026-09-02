package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class BankAccountOwner {
    public String name;
    public BankAccountOwnerAddress address;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final BankAccountOwner owner = new BankAccountOwner();
        public Builder name(String name) { owner.name = name; return this; }
        public Builder address(BankAccountOwnerAddress address) { owner.address = address; return this; }
        public BankAccountOwner build() { return owner; }
    }
}
