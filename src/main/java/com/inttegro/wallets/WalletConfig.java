package com.inttegro.wallets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletConfig {
    public WalletType type;
    @JsonProperty("mobile_money") public WalletMobileMoney mobileMoney;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final WalletConfig wallet = new WalletConfig();
        public Builder type(WalletType type) { wallet.type = type; return this; }
        public Builder mobileMoney(WalletMobileMoney mobileMoney) { wallet.mobileMoney = mobileMoney; return this; }
        public WalletConfig build() { return wallet; }
    }
}
