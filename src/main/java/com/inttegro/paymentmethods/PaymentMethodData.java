package com.inttegro.paymentmethods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.PaymentMethodType;
import java.util.Map;

public class PaymentMethodData {
    public PaymentMethodType type;
    @JsonProperty("mobile_money")
    public MobileMoneyParams mobileMoney;

    public static Builder builder() { return new Builder(); }

    public static PaymentMethodData mobileMoney(java.util.function.Consumer<MobileMoneyParams.Builder> fn) {
        MobileMoneyParams.Builder b = new MobileMoneyParams.Builder();
        fn.accept(b);
        PaymentMethodData data = new PaymentMethodData();
        data.type = PaymentMethodType.MOBILE_MONEY;
        data.mobileMoney = b.build();
        return data;
    }

    public static class Builder {
        private final PaymentMethodData data = new PaymentMethodData();
        public Builder type(PaymentMethodType type) { data.type = type; return this; }
        public Builder mobileMoney(MobileMoneyParams mobileMoney) { data.mobileMoney = mobileMoney; return this; }
        public PaymentMethodData build() { return data; }
    }
}
