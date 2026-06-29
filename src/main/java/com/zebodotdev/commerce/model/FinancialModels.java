package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FinancialModels {
    public static class PullPushConfig {
        public Boolean enabled;
        @JsonProperty("enabled_at") public String enabledAt;
        public Map<String, Object> mandate;
    }

    public static class WalletMobileMoney {
        public String id;
        @JsonProperty("account_number") public String accountNumber;
        public String network;
    }

    public static class WalletConfig {
        public String type;
        @JsonProperty("mobile_money") public WalletMobileMoney mobileMoney;
    }

    public static class BankAccountOwnerAddress {
        public String id;
        @JsonProperty("application_id") public String applicationId;
        public String name;
        public String phone;
        @JsonProperty("line_1") public String line1;
        @JsonProperty("line_2") public String line2;
        public String city;
        public String region;
        @JsonProperty("post_code") public String postCode;
        public String country;
    }

    public static class BankAccountOwner {
        public String name;
        public BankAccountOwnerAddress address;
    }

    public static class GhanaBankAccount {
        @JsonProperty("bank_name") public String bankName;
        public String branch;
        public String number;
        @JsonProperty("sort_code") public String sortCode;
        @JsonProperty("swift_code") public String swiftCode;
        @JsonProperty("holder") public BankAccountOwner holder;
    }

    public static class BankAccountConfig {
        public String id;
        public String type;
        @JsonProperty("ghana_bank_account") public GhanaBankAccount ghanaBankAccount;
    }

    public static class FinancialAccountCreateParams {
        public String label;
        public String type;
        public String reference;
        public String currency;
        public String description;
        @JsonProperty("pull_configuration") public PullPushConfig pullConfiguration;
        @JsonProperty("push_configuration") public PullPushConfig pushConfiguration;
        public WalletConfig wallet;
        @JsonProperty("bank_account") public BankAccountConfig bankAccount;
        @JsonProperty("dosh_account") public Map<String, Object> doshAccount;
        @JsonProperty("custom_data") public Map<String, String> customData;
        public BankAccountOwner owner;
    }

    public static class FinancialAccount {
        public String id;
        public String label;
        public String type;
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

    public static class FinancialAccountUpdateParams {
        @JsonProperty("account_id") public String accountId;
        public String label;
        public String description;
        public String reference;
        @JsonProperty("custom_data") public Map<String, String> customData;
        public BankAccountOwner owner;
    }

    public static class FinancialAccountToggleParams {
        @JsonProperty("account_id") public String accountId;
        @JsonProperty("unset_as_payout_destination") public Boolean unsetAsPayoutDestination;
    }

    public static class FinancialAccountResponse { public FinancialAccount account; }

    public static class PageFinancialAccountsParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;
    }

    public static class FinancialAccountsPage {
        public Integer number;
        public Integer size;
        public FinancialAccount[] accounts;
    }

    public static class FinancialAccountsPageResponse {
        public FinancialAccountsPage page;
    }
}
