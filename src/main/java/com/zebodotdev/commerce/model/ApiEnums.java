package com.zebodotdev.commerce.model;

/** String constants for every enum published by the Inttegro API. */
public final class ApiEnums {
    private ApiEnums() {}

    public static final class AppManagementRole { public static final String PARENT = "parent", CHILD = "child"; private AppManagementRole() {} }
    public static final class AppCredentialOwner { public static final String CHILD = "child", PARENT = "parent"; private AppCredentialOwner() {} }
    public static final class AppRelationshipKind { public static final String PLACEMENT = "placement"; private AppRelationshipKind() {} }
    public static final class AppRelationshipStatus { public static final String ACTIVE = "active", INACTIVE = "inactive", SUSPENDED = "suspended", REVOKED = "revoked"; private AppRelationshipStatus() {} }
    public static final class SecretKeyTokenType { public static final String BEARER = "bearer"; private SecretKeyTokenType() {} }
    public static final class SecretKeyStatus { public static final String ACTIVE = "active", REVOKED = "revoked", EXPIRED = "expired"; private SecretKeyStatus() {} }
    public static final class SecretKeyAuthResult { public static final String SUCCEEDED = "succeeded", FAILED = "failed"; private SecretKeyAuthResult() {} }

    public static final class FileStatus { public static final String UPLOADING = "uploading", PROCESSING = "processing", AVAILABLE = "available", FAILED = "failed", DELETED = "deleted"; private FileStatus() {} }
    public static final class FileDisposition { public static final String ATTACHMENT = "attachment", INLINE = "inline"; private FileDisposition() {} }
    public static final class FileDelivery { public static final String STREAM = "stream", REDIRECT = "redirect"; private FileDelivery() {} }
    public static final class FileScanStatus { public static final String PENDING = "pending", PASSED = "passed", FAILED = "failed", SKIPPED = "skipped"; private FileScanStatus() {} }
    public static final class FileSourceType { public static final String DIRECT = "direct", UPLOAD_REQUEST = "upload_request", SERVICE = "service"; private FileSourceType() {} }
    public static final class FileStorageEncoding { public static final String IDENTITY = "identity", BROTLI = "br"; private FileStorageEncoding() {} }
    public static final class FileLinkStatus { public static final String ACTIVE = "active", REVOKED = "revoked", EXPIRED = "expired", DISABLED = "disabled"; private FileLinkStatus() {} }
    public static final class FileLinkKind { public static final String PUBLIC = "public"; private FileLinkKind() {} }
    public static final class FileLinkDeliveryMode { public static final String REDIRECT = "redirect", DOWNLOAD = "download", INLINE = "inline"; private FileLinkDeliveryMode() {} }
    public static final class UploadRequestStatus { public static final String PENDING = "pending", UPLOADING = "uploading", FULFILLED = "fulfilled", EXPIRED = "expired", CANCELED = "canceled", FAILED = "failed"; private UploadRequestStatus() {} }
    public static final class UploadReviewDecision { public static final String APPROVED = "approved", REJECTED = "rejected"; private UploadReviewDecision() {} }
    public static final class UploadReviewType { public static final String AUTOMATIC = "automatic", MANUAL = "manual"; private UploadReviewType() {} }

    public static final class PaymentNextActionType { public static final String CONFIRM_PAYMENT = "confirm_payment", EXECUTE = "execute", REDIRECT = "redirect", AUTHORIZE = "authorize", NONE = "none"; private PaymentNextActionType() {} }
    public static final class PaymentConfirmationChannel { public static final String SMS = "sms", EMAIL = "email", PUSH = "push"; private PaymentConfirmationChannel() {} }
    public static final class PaymentMethodType { public static final String MOBILE_MONEY = "mobile_money", BANK_ACCOUNT = "bank_account", CARD = "card", MOTITO = "motito"; private PaymentMethodType() {} }
    public static final class MobileMoneyNetwork { public static final String AIRTEL = "airtel", MTN = "mtn", TELECEL = "telecel", VODAFONE = "vodafone"; private MobileMoneyNetwork() {} }

    public static final class ProductType { public static final String PHYSICAL = "physical", DIGITAL = "digital", SERVICE = "service", VOUCHER = "voucher", CUSTOM = "custom", CAUSE = "cause"; private ProductType() {} }
    public static final class ProductShipmentType { public static final String DELIVERY = "delivery", DOWNLOAD = "download", RENDER = "render", SERVICE = "service", STREAM = "stream"; private ProductShipmentType() {} }
    public static final class ProductShipmentInputType { public static final String DELIVERY = "delivery", DOWNLOAD = "download", RENDER = "render", STREAM = "stream"; private ProductShipmentInputType() {} }
    public static final class LineItemType { public static final String PRODUCT = "product", FEE = "fee", SHIPPING = "shipping"; private LineItemType() {} }
    public static final class PurchaseIntentStatus { public static final String ACTIVE = "active", EXPIRED = "expired", INACTIVE = "inactive", USED = "used"; private PurchaseIntentStatus() {} }
    public static final class PurchaseIntentActivityType { public static final String EXPIRED_VIEWED = "expired_viewed", ORDER_CREATED = "order_created", PAYMENT_FAILED = "payment_failed", PAYMENT_STARTED = "payment_started", VIEWED = "viewed"; private PurchaseIntentActivityType() {} }

    public static final class FinancialAccountType { public static final String WALLET = "wallet", BANK_ACCOUNT = "bank_account", DOSH_ACCOUNT = "dosh_account"; private FinancialAccountType() {} }
    public static final class WalletType { public static final String MOBILE_MONEY = "mobile_money"; private WalletType() {} }
    public static final class BankAccountType { public static final String GHANA_BANK_ACCOUNT = "ghana_bank_account"; private BankAccountType() {} }

    public static final class MessageTemplateChannel { public static final String SMS = "sms", EMAIL = "email"; private MessageTemplateChannel() {} }
    public static final class MessageTemplateStatus { public static final String DRAFT = "draft", PUBLISHED = "published", ARCHIVED = "archived"; private MessageTemplateStatus() {} }
    public static final class MessageTemplateVariableType { public static final String STRING = "string", NUMBER = "number", INTEGER = "integer", BOOLEAN = "boolean", URL = "url", EMAIL = "email", PHONE = "phone", DATE = "date", DATETIME = "datetime", ARRAY = "array"; private MessageTemplateVariableType() {} }
    public static final class MessageTemplateVariableItemType { public static final String STRING = "string", NUMBER = "number", INTEGER = "integer", BOOLEAN = "boolean", URL = "url", EMAIL = "email", PHONE = "phone", DATE = "date", DATETIME = "datetime"; private MessageTemplateVariableItemType() {} }
    public static final class ContentSafetyStatus { public static final String ALLOWED = "allowed", REJECTED = "rejected", QUARANTINED = "quarantined"; private ContentSafetyStatus() {} }

    public static final class OrderDocumentKind { public static final String INVOICE = "invoice", RECEIPT = "receipt"; private OrderDocumentKind() {} }
    public static final class DeliveryChannel { public static final String EMAIL = "email", SMS = "sms"; private DeliveryChannel() {} }
    public static final class CheckoutOrderStatus { public static final String PREPARING = "preparing", REQUIRES_PAYMENT = "requires_payment", COMPLETED = "completed", CANCELED = "canceled", EXPIRED = "expired"; private CheckoutOrderStatus() {} }
    public static final class OrderStatus { public static final String PREPARING = "preparing", REQUIRES_PAYMENT = "requires_payment", PAID = "paid", COMPLETED = "completed", CANCELED = "canceled", EXPIRED = "expired", UNKNOWN = "unknown"; private OrderStatus() {} }
    public static final class OrderPaymentStatus { public static final String INITIATED = "initiated", REQUIRES_ACTION = "requires_action", OVERDUE = "overdue", EXECUTED = "executed", PAID = "paid", CANCELED = "canceled", EXPIRED = "expired", FAILED = "failed", UNKNOWN = "unknown"; private OrderPaymentStatus() {} }
    public static final class PaymentAttemptStatus { public static final String INITIATED = "initiated", EXECUTED = "executed", SUCCEEDED = "succeeded", CANCELED = "canceled", EXPIRED = "expired", FAILED = "failed", UNKNOWN = "unknown"; private PaymentAttemptStatus() {} }
    public static final class CheckoutPaymentStatus { public static final String REQUIRES_ACTION = "requires_action", PROCESSING = "processing", SUCCEEDED = "succeeded", FAILED = "failed", CANCELLED = "cancelled"; private CheckoutPaymentStatus() {} }
    public static final class PaymentResponseStatus { public static final String PENDING = "pending", REQUIRES_CONFIRMATION = "requires_confirmation", PROCESSING = "processing", SUCCEEDED = "succeeded", FAILED = "failed"; private PaymentResponseStatus() {} }
    public static final class OrderCreatedFromResourceType { public static final String PURCHASE_INTENT = "purchase_intent"; private OrderCreatedFromResourceType() {} }

    public static final class RefundReason { public static final String REQUESTED_BY_CUSTOMER = "requested_by_customer", DUPLICATE = "duplicate", FRAUDULENT = "fraudulent", ORDER_CANCELED = "order_canceled", ITEM_RETURNED = "item_returned", ITEM_DAMAGED = "item_damaged", ITEM_NOT_RECEIVED = "item_not_received", ITEM_NOT_AS_DESCRIBED = "item_not_as_described", CUSTOM = "custom"; private RefundReason() {} }
    public static final class RefundStatus { public static final String CANCELED = "canceled", FAILED = "failed", PENDING = "pending", PROCESSING = "processing", SUCCEEDED = "succeeded"; private RefundStatus() {} }
    public static final class BalanceTransactionType { public static final String PAYMENT = "payment", REFUND = "refund"; private BalanceTransactionType() {} }
    public static final class PayoutStatus { public static final String INITIALIZED = "initialized", SCHEDULED = "scheduled", PROCESSING = "processing", EXECUTING = "executing", SUCCEEDED = "succeeded", INVALID = "invalid", CANCELED = "canceled"; private PayoutStatus() {} }

    public static final class ChimeRecipientType { public static final String PHONE = "phone", EMAIL = "email"; private ChimeRecipientType() {} }
    public static final class ChimeTransport { public static final String SMS = "sms", EMAIL = "email"; private ChimeTransport() {} }
    public static final class ChimeEmailSchemaKind { public static final String GMAIL_VIEW_ACTION = "gmail_view_action", SCHEMA_ORG_ORDER = "schema_org_order", SCHEMA_ORG_INVOICE = "schema_org_invoice"; private ChimeEmailSchemaKind() {} }

    public static final class OTPAlphabetType { public static final String NUMERIC = "numeric", ALPHA = "alpha", ALPHANUMERIC = "alphanumeric"; private OTPAlphabetType() {} }
    public static final class OTPStatus { public static final String CANCELED = "canceled", EXPIRED = "expired", PENDING = "pending", PENDING_DELIVERY = "pending_delivery", PENDING_VERIFICATION = "pending_verification", VERIFIED = "verified"; private OTPStatus() {} }
    public static final class OTPTransmissionStatus { public static final String DELIVERED = "delivered", FAILED = "failed", SUBMITTED = "submitted"; private OTPTransmissionStatus() {} }
    public static final class OTPVerificationVerdict { public static final String FAIL = "fail", PASS = "pass"; private OTPVerificationVerdict() {} }
}
