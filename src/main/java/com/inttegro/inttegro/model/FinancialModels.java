package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FinancialModels {
    public static class PullPushConfig {
        public Boolean enabled;
        @JsonProperty("enabled_at") public String enabledAt;
        public Map<String, Object> mandate;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PullPushConfig config = new PullPushConfig();
            public Builder enabled(Boolean enabled) { config.enabled = enabled; return this; }
            public Builder enabled(boolean enabled) { config.enabled = enabled; return this; }
            public Builder enabledAt(String enabledAt) { config.enabledAt = enabledAt; return this; }
            public Builder mandate(Map<String, Object> mandate) { config.mandate = mandate; return this; }
            public PullPushConfig build() { return config; }
        }
    }

    public static class WalletMobileMoney {
        public String id;
        @JsonProperty("account_number") public String accountNumber;
        public String network;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final WalletMobileMoney mobileMoney = new WalletMobileMoney();
            public Builder id(String id) { mobileMoney.id = id; return this; }
            public Builder accountNumber(String accountNumber) { mobileMoney.accountNumber = accountNumber; return this; }
            public Builder network(String network) { mobileMoney.network = network; return this; }
            public WalletMobileMoney build() { return mobileMoney; }
        }
    }

    public static class WalletConfig {
        public String type;
        @JsonProperty("mobile_money") public WalletMobileMoney mobileMoney;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final WalletConfig wallet = new WalletConfig();
            public Builder type(String type) { wallet.type = type; return this; }
            public Builder mobileMoney(WalletMobileMoney mobileMoney) { wallet.mobileMoney = mobileMoney; return this; }
            public WalletConfig build() { return wallet; }
        }
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

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final BankAccountOwnerAddress address = new BankAccountOwnerAddress();
            public Builder id(String id) { address.id = id; return this; }
            public Builder applicationId(String applicationId) { address.applicationId = applicationId; return this; }
            public Builder name(String name) { address.name = name; return this; }
            public Builder phone(String phone) { address.phone = phone; return this; }
            public Builder line1(String line1) { address.line1 = line1; return this; }
            public Builder line2(String line2) { address.line2 = line2; return this; }
            public Builder city(String city) { address.city = city; return this; }
            public Builder region(String region) { address.region = region; return this; }
            public Builder postCode(String postCode) { address.postCode = postCode; return this; }
            public Builder country(String country) { address.country = country; return this; }
            public BankAccountOwnerAddress build() { return address; }
        }
    }

    public static class BankAccountOwner {
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

    public static class GhanaBankAccount {
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

    public static class BankAccountConfig {
        public String id;
        public String type;
        @JsonProperty("ghana_bank_account") public GhanaBankAccount ghanaBankAccount;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final BankAccountConfig config = new BankAccountConfig();
            public Builder id(String id) { config.id = id; return this; }
            public Builder type(String type) { config.type = type; return this; }
            public Builder ghanaBankAccount(GhanaBankAccount ghanaBankAccount) { config.ghanaBankAccount = ghanaBankAccount; return this; }
            public BankAccountConfig build() { return config; }
        }
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

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FinancialAccountCreateParams params = new FinancialAccountCreateParams();
            public Builder label(String label) { params.label = label; return this; }
            public Builder type(String type) { params.type = type; return this; }
            public Builder reference(String reference) { params.reference = reference; return this; }
            public Builder currency(String currency) { params.currency = currency; return this; }
            public Builder description(String description) { params.description = description; return this; }
            public Builder pullConfiguration(PullPushConfig pullConfiguration) { params.pullConfiguration = pullConfiguration; return this; }
            public Builder pushConfiguration(PullPushConfig pushConfiguration) { params.pushConfiguration = pushConfiguration; return this; }
            public Builder wallet(WalletConfig wallet) { params.wallet = wallet; return this; }
            public Builder bankAccount(BankAccountConfig bankAccount) { params.bankAccount = bankAccount; return this; }
            public Builder doshAccount(Map<String, Object> doshAccount) { params.doshAccount = doshAccount; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder owner(BankAccountOwner owner) { params.owner = owner; return this; }
            public FinancialAccountCreateParams build() { return params; }
        }
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

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FinancialAccountUpdateParams params = new FinancialAccountUpdateParams();
            public Builder accountId(String accountId) { params.accountId = accountId; return this; }
            public Builder label(String label) { params.label = label; return this; }
            public Builder description(String description) { params.description = description; return this; }
            public Builder reference(String reference) { params.reference = reference; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder owner(BankAccountOwner owner) { params.owner = owner; return this; }
            public FinancialAccountUpdateParams build() { return params; }
        }
    }

    public static class FinancialAccountToggleParams {
        @JsonProperty("account_id") public String accountId;
        @JsonProperty("unset_as_payout_destination") public Boolean unsetAsPayoutDestination;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FinancialAccountToggleParams params = new FinancialAccountToggleParams();
            public Builder accountId(String accountId) { params.accountId = accountId; return this; }
            public Builder unsetAsPayoutDestination(Boolean unsetAsPayoutDestination) { params.unsetAsPayoutDestination = unsetAsPayoutDestination; return this; }
            public Builder unsetAsPayoutDestination(boolean unsetAsPayoutDestination) { params.unsetAsPayoutDestination = unsetAsPayoutDestination; return this; }
            public FinancialAccountToggleParams build() { return params; }
        }
    }

    public static class FinancialAccountLookupParams {
        @JsonProperty("account_id") public String accountId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FinancialAccountLookupParams params = new FinancialAccountLookupParams();
            public Builder accountId(String accountId) { params.accountId = accountId; return this; }
            public FinancialAccountLookupParams build() { return params; }
        }
    }

    public static class FinancialAccountResponse { public FinancialAccount account; }

    public static class PageFinancialAccountsParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PageFinancialAccountsParams params = new PageFinancialAccountsParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public PageFinancialAccountsParams build() { return params; }
        }
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
