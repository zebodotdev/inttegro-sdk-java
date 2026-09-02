package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodOwner {
    public String name;
    public PaymentMethodOwnerAddress address;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final PaymentMethodOwner owner = new PaymentMethodOwner();
        public Builder name(String name) { owner.name = name; return this; }
        public Builder address(PaymentMethodOwnerAddress address) { owner.address = address; return this; }
        public PaymentMethodOwner build() { return owner; }
    }
}
