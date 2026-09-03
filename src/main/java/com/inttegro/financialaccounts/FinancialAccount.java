package com.inttegro.financialaccounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

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
    @JsonProperty("dosh_account") public Map<String, Object> doshAccount;
    public Object verification;
    @JsonProperty("archived_at") public String archivedAt;
    @JsonProperty("disconnected_at") public String disconnectedAt;
    @JsonProperty("created_at") public String createdAt;
    @JsonProperty("custom_data") public Map<String, String> customData;
    public BankAccountOwner owner;
}
