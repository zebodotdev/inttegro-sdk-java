package com.inttegro.financialaccounts;

import java.time.OffsetDateTime;
import com.inttegro.CustomData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.bankaccounts.BankAccountConfig;
import com.inttegro.bankaccounts.BankAccountOwner;
import com.inttegro.wallets.WalletConfig;

public class FinancialAccount {
    public String id;
    public String label;
    public FinancialAccountType type;
    public String reference;
    public String currency;
    public String description;
    @JsonProperty("pull_configuration") public PullPushConfig pullConfiguration;
    @JsonProperty("push_configuration") public PullPushConfig pushConfiguration;
    public WalletConfig wallet;
    @JsonProperty("bank_account") public BankAccountConfig bankAccount;
    @JsonProperty("dosh_account") public DoshAccount doshAccount;
    public FinancialAccountVerification verification;
    @JsonProperty("archived_at") public OffsetDateTime archivedAt;
    @JsonProperty("disconnected_at") public OffsetDateTime disconnectedAt;
    @JsonProperty("created_at") public OffsetDateTime createdAt;
    @JsonProperty("custom_data") public CustomData customData;
    public BankAccountOwner owner;
}
