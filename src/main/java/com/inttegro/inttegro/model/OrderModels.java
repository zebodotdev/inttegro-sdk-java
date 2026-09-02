package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.inttegro.model.CommonModels.ChimeRecipientType;
import com.inttegro.inttegro.model.CommonModels.LineItemType;
import com.inttegro.inttegro.model.CommonModels.PaymentMethodType;
import com.inttegro.inttegro.model.CommonModels.Money;
import com.inttegro.inttegro.model.CustomerModels.Address;
import com.inttegro.inttegro.model.CustomerModels.BillingDetails;
import com.inttegro.inttegro.model.CustomerModels.CustomerData;
import com.inttegro.inttegro.model.CustomerModels.Shipping;
import com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodObject;
import com.inttegro.inttegro.model.RefundModels.Refund;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderModels {
    public static class FeeLineItem {
        public String id;
        public String label;
        public String description;
        @JsonProperty("tax_code")
        public String taxCode;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        public Money amount;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final FeeLineItem item = new FeeLineItem();
            public Builder id(String id) { item.id = id; return this; }
            public Builder label(String label) { item.label = label; return this; }
            public Builder description(String description) { item.description = description; return this; }
            public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
            public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
            public Builder amount(Money amount) { item.amount = amount; return this; }
            public FeeLineItem build() { return item; }
        }
    }

    public static class ShippingLineItem {
        public String id;
        public Money fee;
        @JsonProperty("tax_code")
        public String taxCode;
        @JsonProperty("custom_data")
        public Map<String, String> customData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ShippingLineItem item = new ShippingLineItem();
            public Builder id(String id) { item.id = id; return this; }
            public Builder fee(Money fee) { item.fee = fee; return this; }
            public Builder taxCode(String taxCode) { item.taxCode = taxCode; return this; }
            public Builder customData(Map<String, String> customData) { item.customData = customData; return this; }
            public ShippingLineItem build() { return item; }
        }
    }

    public static class ProductLineItem {
        public String id;
        public String type;
        public String name;
        public String about;
        public Long quantity;
        public Money price;
        public String reference;
        @JsonProperty("tax_code")
        public String taxCode;
        @JsonProperty("custom_data")
        public Map<String, String> customData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductLineItem item = new ProductLineItem();
            public Builder id(String id) { item.id = id; return this; }
            public Builder type(String type) { item.type = type; return this; }
            public Builder name(String name) { item.name = name; return this; }
            public Builder about(String about) { item.about = about; return this; }
            public Builder quantity(long qty) { item.quantity = qty; return this; }
            public Builder price(Money price) { item.price = price; return this; }
            public Builder reference(String ref) { item.reference = ref; return this; }
            public Builder taxCode(String tax) { item.taxCode = tax; return this; }
            public Builder customData(Map<String, String> data) { item.customData = data; return this; }
            public ProductLineItem build() { return item; }
        }
    }

    public static class OrderLineItem {
        public LineItemType type;
        public ProductLineItem product;
        public FeeLineItem fee;
        public ShippingLineItem shipping;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderLineItem item = new OrderLineItem();
            public Builder type(LineItemType type) { item.type = type; return this; }
            public Builder product(ProductLineItem product) { item.product = product; return this; }
            public Builder fee(FeeLineItem fee) { item.fee = fee; return this; }
            public Builder shipping(ShippingLineItem shipping) { item.shipping = shipping; return this; }
            public OrderLineItem build() { return item; }
        }

        public static OrderLineItem product(ProductLineItem product) {
            OrderLineItem li = new OrderLineItem();
            li.type = LineItemType.PRODUCT;
            li.product = product;
            return li;
        }

        public static OrderLineItem product(Consumer<ProductLineItem.Builder> fn) {
            ProductLineItem.Builder b = new ProductLineItem.Builder();
            fn.accept(b);
            return product(b.build());
        }

        public static OrderLineItem fee(FeeLineItem fee) {
            OrderLineItem li = new OrderLineItem();
            li.type = LineItemType.FEE;
            li.fee = fee;
            return li;
        }

        public static OrderLineItem fee(Consumer<FeeLineItem> fn) {
            FeeLineItem f = new FeeLineItem();
            fn.accept(f);
            return fee(f);
        }

        public static OrderLineItem shipping(ShippingLineItem shipping) {
            OrderLineItem li = new OrderLineItem();
            li.type = LineItemType.SHIPPING;
            li.shipping = shipping;
            return li;
        }

        public static OrderLineItem shipping(Consumer<ShippingLineItem> fn) {
            ShippingLineItem s = new ShippingLineItem();
            fn.accept(s);
            return shipping(s);
        }
    }

    public static class CheckoutSettings {
        @JsonProperty("redirect_url")
        public String redirectUrl;
        @JsonProperty("cancel_url")
        public String cancelUrl;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CheckoutSettings settings = new CheckoutSettings();
            public Builder redirectUrl(String redirectUrl) { settings.redirectUrl = redirectUrl; return this; }
            public Builder cancelUrl(String cancelUrl) { settings.cancelUrl = cancelUrl; return this; }
            public CheckoutSettings build() { return settings; }
        }
    }

    public static class OrderPayoutSettings {
        public OrderPayoutDestination destination;
        @JsonProperty("enable_fx")
        public Boolean enableFX;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayoutSettings settings = new OrderPayoutSettings();
            public Builder destination(OrderPayoutDestination destination) { settings.destination = destination; return this; }
            public Builder enableFX(Boolean enableFX) { settings.enableFX = enableFX; return this; }
            public Builder enableFX(boolean enableFX) { settings.enableFX = enableFX; return this; }
            public OrderPayoutSettings build() { return settings; }
        }
    }

    public static class OrderPayoutDestination {
        @JsonProperty("financial_account_id")
        public String financialAccountId;
        @JsonProperty("financial_account_data")
        public OrderPayoutFinancialAccount financialAccountData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayoutDestination destination = new OrderPayoutDestination();
            public Builder financialAccountId(String financialAccountId) { destination.financialAccountId = financialAccountId; return this; }
            public Builder financialAccountData(OrderPayoutFinancialAccount financialAccountData) { destination.financialAccountData = financialAccountData; return this; }
            public OrderPayoutDestination build() { return destination; }
        }
    }

    public static class OrderPayoutFinancialAccount {
        public String type;
        public OrderPayoutWallet wallet;
        @JsonProperty("bank_account") public FinancialModels.BankAccountConfig bankAccount;
        @JsonProperty("dosh_account") public Map<String, Object> doshAccount;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayoutFinancialAccount account = new OrderPayoutFinancialAccount();
            public Builder type(String type) { account.type = type; return this; }
            public Builder wallet(OrderPayoutWallet wallet) { account.wallet = wallet; return this; }
            public Builder bankAccount(FinancialModels.BankAccountConfig bankAccount) { account.bankAccount = bankAccount; return this; }
            public Builder doshAccount(Map<String, Object> doshAccount) { account.doshAccount = doshAccount; return this; }
            public OrderPayoutFinancialAccount build() { return account; }
        }
    }

    public static class OrderPayoutWallet {
        public String type;
        @JsonProperty("mobile_money")
        public OrderPayoutMobileMoney mobileMoney;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayoutWallet wallet = new OrderPayoutWallet();
            public Builder type(String type) { wallet.type = type; return this; }
            public Builder mobileMoney(OrderPayoutMobileMoney mobileMoney) { wallet.mobileMoney = mobileMoney; return this; }
            public OrderPayoutWallet build() { return wallet; }
        }
    }

    public static class OrderPayoutMobileMoney {
        @JsonProperty("account_number")
        public String accountNumber;
        public String network;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayoutMobileMoney mobileMoney = new OrderPayoutMobileMoney();
            public Builder accountNumber(String accountNumber) { mobileMoney.accountNumber = accountNumber; return this; }
            public Builder network(String network) { mobileMoney.network = network; return this; }
            public OrderPayoutMobileMoney build() { return mobileMoney; }
        }
    }

    public static class OrderCreateParams {
        @JsonProperty("request_meta")
        public RequestMeta requestMeta;
        @JsonProperty("customer_data")
        public CustomerData customerData;
        @JsonProperty("customer_id")
        public String customerId;
        @JsonProperty("payment_method_id")
        public String paymentMethodId;
        @JsonProperty("payment_method_data")
        public com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData paymentMethodData;
        @JsonProperty("statement_descriptor")
        public String statementDescriptor;
        @JsonProperty("statement_descriptor_prefix")
        public String statementDescriptorPrefix;
        @JsonProperty("execute_payment")
        public Boolean executePayment;
        public Boolean finalize;
        @Deprecated
        @JsonProperty("idempotency_key")
        public String idempotencyKey;
        @JsonProperty("checkout_settings")
        public CheckoutSettings checkoutSettings;
        @JsonProperty("payout_settings")
        public OrderPayoutSettings payoutSettings;
        public String number;
        @JsonProperty("receipt_number")
        public String receiptNumber;
        @JsonProperty("line_items")
        public List<OrderLineItem> lineItems;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        @JsonProperty("billing_details")
        public BillingDetails billingDetails;
        public Shipping shipping;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final OrderCreateParams params = new OrderCreateParams();
            private final List<OrderLineItem> items = new ArrayList<>();
            public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
            public Builder customerData(CustomerData customer) { params.customerData = customer; return this; }
            public Builder customerId(String customerId) { params.customerId = customerId; return this; }
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public Builder paymentMethodData(com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData data) { params.paymentMethodData = data; return this; }
            public Builder statementDescriptor(String desc) { params.statementDescriptor = desc; return this; }
            public Builder statementDescriptorPrefix(String prefix) { params.statementDescriptorPrefix = prefix; return this; }
            public Builder executePayment(boolean execute) { params.executePayment = execute; return this; }
            public Builder finalizeOrder(boolean finalize) { params.finalize = finalize; return this; }
            @Deprecated
            public Builder idempotencyKey(String key) { params.idempotencyKey = key; return this; }
            public Builder checkoutSettings(CheckoutSettings settings) { params.checkoutSettings = settings; return this; }
            public Builder payoutSettings(OrderPayoutSettings settings) { params.payoutSettings = settings; return this; }
            public Builder number(String number) { params.number = number; return this; }
            public Builder receiptNumber(String receiptNumber) { params.receiptNumber = receiptNumber; return this; }
            public Builder lineItem(OrderLineItem item) { this.items.add(item); return this; }
            public Builder customData(Map<String, String> data) { params.customData = data; return this; }
            public Builder billingDetails(BillingDetails details) { params.billingDetails = details; return this; }
            public Builder shipping(Shipping shipping) { params.shipping = shipping; return this; }
            public OrderCreateParams build() { params.lineItems = items; return params; }
        }
    }

    public static class OrderLookupParams {
        @JsonProperty("order_id") public String orderId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderLookupParams params = new OrderLookupParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public OrderLookupParams build() { return params; }
        }
    }
    public static class OrderUpdateParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("clear_payment_method") public Boolean clearPaymentMethod;
        @JsonProperty("custom_data") public Map<String, String> customData;
        @JsonProperty("invoice_settings") public Map<String, Object> invoiceSettings;
        public Boolean finalize;
        @JsonProperty("line_items") public List<OrderLineItem> lineItems;
        public String number;
        @JsonProperty("receipt_number") public String receiptNumber;
        @JsonProperty("payment_method_data") public com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData paymentMethodData;
        @JsonProperty("payment_method_id") public String paymentMethodId;
        @JsonProperty("statement_descriptor") public String statementDescriptor;
        @JsonProperty("statement_descriptor_prefix") public String statementDescriptorPrefix;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final OrderUpdateParams params = new OrderUpdateParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder clearPaymentMethod(Boolean clearPaymentMethod) { params.clearPaymentMethod = clearPaymentMethod; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public Builder invoiceSettings(Map<String, Object> invoiceSettings) { params.invoiceSettings = invoiceSettings; return this; }
            public Builder finalizeOrder(Boolean finalize) { params.finalize = finalize; return this; }
            public Builder lineItems(List<OrderLineItem> lineItems) { params.lineItems = lineItems; return this; }
            public Builder number(String number) { params.number = number; return this; }
            public Builder receiptNumber(String receiptNumber) { params.receiptNumber = receiptNumber; return this; }
            public Builder paymentMethodData(com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public Builder statementDescriptor(String statementDescriptor) { params.statementDescriptor = statementDescriptor; return this; }
            public Builder statementDescriptorPrefix(String statementDescriptorPrefix) { params.statementDescriptorPrefix = statementDescriptorPrefix; return this; }
            public OrderUpdateParams build() { return params; }
        }
    }
    public static class OrderPayParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;
        @JsonProperty("payment_method_id") public String paymentMethodId;
        @JsonProperty("payment_method_data") public com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData paymentMethodData;
        @JsonProperty("paid_out_of_band") public Boolean paidOutOfBand;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPayParams params = new OrderPayParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public Builder paymentMethodId(String paymentMethodId) { params.paymentMethodId = paymentMethodId; return this; }
            public Builder paymentMethodData(com.inttegro.inttegro.model.PaymentMethodModels.PaymentMethodData paymentMethodData) { params.paymentMethodData = paymentMethodData; return this; }
            public Builder paidOutOfBand(Boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
            public Builder paidOutOfBand(boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
            public OrderPayParams build() { return params; }
        }
    }
    public static class OrderConfirmParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;
        public String token;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderConfirmParams params = new OrderConfirmParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public Builder token(String token) { params.token = token; return this; }
            public OrderConfirmParams build() { return params; }
        }
    }
    public static class OrderRequestConfirmationParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderRequestConfirmationParams params = new OrderRequestConfirmationParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public OrderRequestConfirmationParams build() { return params; }
        }
    }
    public static class OrderFinalizeParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderFinalizeParams params = new OrderFinalizeParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public OrderFinalizeParams build() { return params; }
        }
    }
    public static class OrderSendInvoiceParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final OrderSendInvoiceParams params = new OrderSendInvoiceParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
            public OrderSendInvoiceParams build() { return params; }
        }
    }
    public static class OrderSendReceiptParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final OrderSendReceiptParams params = new OrderSendReceiptParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta meta) { params.requestMeta = meta; return this; }
            public OrderSendReceiptParams build() { return params; }
        }
    }
    public static class OrderCompleteParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("paid_out_of_band") public Boolean paidOutOfBand;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderCompleteParams params = new OrderCompleteParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder paidOutOfBand(Boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
            public Builder paidOutOfBand(boolean paidOutOfBand) { params.paidOutOfBand = paidOutOfBand; return this; }
            public OrderCompleteParams build() { return params; }
        }
    }
    public static class OrderCancelParams {
        @JsonProperty("order_id") public String orderId;
        @JsonProperty("request_meta") public RequestMeta requestMeta;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderCancelParams params = new OrderCancelParams();
            public Builder orderId(String orderId) { params.orderId = orderId; return this; }
            public Builder requestMeta(RequestMeta requestMeta) { params.requestMeta = requestMeta; return this; }
            public OrderCancelParams build() { return params; }
        }
    }
    public static class OrderPageParams {
        @JsonProperty("page_number") public Integer pageNumber;
        @JsonProperty("page_size") public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final OrderPageParams params = new OrderPageParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public OrderPageParams build() { return params; }
        }
    }

    public static class PaymentAttempt {
        @JsonProperty("payment_method_type") public String paymentMethodType;
        @JsonProperty("payment_method_id") public String paymentMethodId;
        public String reference;
        public String status;
        @JsonProperty("initiated_at") public String initiatedAt;
        @JsonProperty("succeeded_at") public String succeededAt;
        @JsonProperty("failed_at") public String failedAt;
    }

    public static class ConfirmPaymentRequest {
        public String id;
        public String recipient;
        @JsonProperty("sent_via") public String sentVia;
        @JsonProperty("token_size") public Integer tokenSize;
        @JsonProperty("sender_id") public String senderId;
    }

    public static class ConfirmPaymentAction {
        @JsonProperty("expires_at") public String expiresAt;
        public String scheme;
        public ConfirmPaymentRequest request;
    }

    public static class RedirectAction { public String url; }

    public static class PaymentNextAction {
        public String type;
        @JsonProperty("confirm_payment") public ConfirmPaymentAction confirmPayment;
        public Object execute;
        public RedirectAction redirect;
    }

    public static class Payment {
        public String id;
        public String status;
        @JsonProperty("statement_descriptor") public String statementDescriptor;
        public Money amount;
        @JsonProperty("payment_method") public PaymentMethodObject paymentMethod;
        @JsonProperty("latest_attempt") public PaymentAttempt latestAttempt;
        @JsonProperty("next_action") public PaymentNextAction nextAction;
        @JsonProperty("balance_transaction") public BalanceModels.BalanceTransaction balanceTransaction;
        @JsonProperty("payout_configuration") public BalanceModels.PayoutConfiguration payoutConfiguration;
        @JsonProperty("initiated_at") public String initiatedAt;
        @JsonProperty("executed_at") public String executedAt;
        @JsonProperty("paid_at") public String paidAt;
        @JsonProperty("failed_at") public String failedAt;
    }

    public static class InvoiceLink { public String url; }
    public static class InvoiceFormat { public InvoiceLink web; public InvoiceLink pdf; }
    public static class Invoice {
        public String id;
        public String number;
        public InvoiceFormat format;
        public Object deliveries;
    }

    public static class LineItemGroup {
        @JsonProperty("line_items") public List<OrderLineItem> lineItems;
        public Money total;
    }

    public static class Order {
        public String id;
        public String status;
        public String number;
        @JsonProperty("receipt_number") public String receiptNumber;
        @JsonProperty("customer_id") public String customerId;
        public CustomerData customer;
        @JsonProperty("billing_details") public BillingDetails billingDetails;
        public Shipping shipping;
        @JsonProperty("line_items") public List<OrderLineItem> lineItems;
        @JsonProperty("line_item_group") public LineItemGroup lineItemGroup;
        public Payment payment;
        @JsonProperty("payment_status") public String paymentStatus;
        @JsonProperty("payment_method_id") public String paymentMethodId;
        @JsonProperty("statement_descriptor") public String statementDescriptor;
        @JsonProperty("checkout_settings") public CheckoutSettings checkoutSettings;
        @JsonProperty("initiated_at") public String initiatedAt;
        @JsonProperty("sealed_at") public String sealedAt;
        @JsonProperty("completed_at") public String completedAt;
        @JsonProperty("expires_at") public String expiresAt;
        @JsonProperty("created_at") public String createdAt;
        @JsonProperty("updated_at") public String updatedAt;
        @JsonProperty("paid_at") public String paidAt;
        @JsonProperty("cancelled_at") public String cancelledAt;
        public Invoice invoice;
        public List<Refund> refunds;
    }

    public static class CreateOrderResponse { public Order order; @JsonProperty("redirect_url") public String redirectUrl; }
    public static class LookupOrderResponse { public Order order; }
    public static class OrderDocumentDeliveryResponse {
        public Order order;
        public OrderDocumentDelivery delivery;
    }
    public static class OrderDocumentDelivery {
        @JsonProperty("document_kind") public String documentKind;
        @JsonProperty("document_url") public String documentUrl;
        @JsonProperty("sent_channels") public List<String> sentChannels;
        @JsonProperty("failed_channels") public List<String> failedChannels;
        public List<OrderDocumentDeliveryAttempt> deliveries;
        public List<OrderDocumentDeliveryAttempt> failures;
    }
    public static class OrderDocumentDeliveryAttempt {
        public String channel;
        @JsonProperty("chime_id") public String chimeId;
        public String error;
    }
    public static class PaymentResponse {
        @JsonProperty("payment_id") public String paymentId;
        @JsonProperty("order_id") public String orderId;
        public String status;
        @JsonProperty("requires_confirmation") public Boolean requiresConfirmation;
        @JsonProperty("confirmation_sent") public Boolean confirmationSent;
    }

    public static class OrderSummary {
        public String id;
        @JsonProperty("receipt_number") public String receiptNumber;
        @JsonProperty("line_item_group") public LineItemGroupSummary lineItemGroup;
        @JsonProperty("initiated_at") public String initiatedAt;
        @JsonProperty("completed_at") public String completedAt;
        @JsonProperty("sealed_at") public String sealedAt;
        public String status;
        public CustomerSummary customer;
    }

    public static class LineItemGroupSummary { @JsonProperty("products_count") public Integer productsCount; public Money total; }
    public static class CustomerSummary { public String id; public String name; public String email; @JsonProperty("phone_number") public String phoneNumber; public String suffix; public String title; }
    public static class PageOrdersResponse { public Page<OrderSummary> page; }

    public static class Page<T> {
        public Integer number;
        public Integer size;
        public List<T> orders;
        public List<T> payouts;
        public List<T> transactions;
    }
}
