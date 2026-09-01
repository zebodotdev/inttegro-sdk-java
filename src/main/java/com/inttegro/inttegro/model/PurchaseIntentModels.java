package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.inttegro.model.CommonModels.Money;

import java.util.List;

public class PurchaseIntentModels {
    public static class PurchaseIntentProductSelector {
        public String id;
        @JsonProperty("variant_set_id")
        public String variantSetId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PurchaseIntentProductSelector product = new PurchaseIntentProductSelector();
            public Builder id(String id) { product.id = id; return this; }
            public Builder variantSetId(String variantSetId) { product.variantSetId = variantSetId; return this; }
            public PurchaseIntentProductSelector build() { return product; }
        }
    }

    public static class PurchaseIntentPriceAmount {
        public String currency;
        public Long value;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PurchaseIntentPriceAmount amount = new PurchaseIntentPriceAmount();
            public Builder currency(String currency) { amount.currency = currency; return this; }
            public Builder value(Long value) { amount.value = value; return this; }
            public PurchaseIntentPriceAmount build() { return amount; }
        }
    }

    public static class PurchaseIntentPriceSelector {
        public String id;
        public PurchaseIntentPriceAmount nominal;
        public PurchaseIntentPriceSelector original;
        @JsonProperty("original_id")
        public String originalId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PurchaseIntentPriceSelector price = new PurchaseIntentPriceSelector();
            public Builder id(String id) { price.id = id; return this; }
            public Builder nominal(PurchaseIntentPriceAmount nominal) { price.nominal = nominal; return this; }
            public Builder original(PurchaseIntentPriceSelector original) { price.original = original; return this; }
            public Builder originalId(String originalId) { price.originalId = originalId; return this; }
            public PurchaseIntentPriceSelector build() { return price; }
        }
    }

    public static class PurchaseIntentQuantity {
        public Integer min;
        public Integer max;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PurchaseIntentQuantity quantity = new PurchaseIntentQuantity();
            public Builder min(Integer min) { quantity.min = min; return this; }
            public Builder max(Integer max) { quantity.max = max; return this; }
            public PurchaseIntentQuantity build() { return quantity; }
        }
    }

    public static class PurchaseIntentUsage {
        @JsonProperty("single_use")
        public Boolean singleUse;
        @JsonProperty("multi_use")
        public Boolean multiUse;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PurchaseIntentUsage usage = new PurchaseIntentUsage();
            public Builder singleUse(Boolean singleUse) { usage.singleUse = singleUse; return this; }
            public Builder multiUse(Boolean multiUse) { usage.multiUse = multiUse; return this; }
            public PurchaseIntentUsage build() { return usage; }
        }
    }

    public static class CreatePurchaseIntentParams {
        public PurchaseIntentProductSelector product;
        @JsonProperty("product_id")
        public String productId;
        public PurchaseIntentPriceSelector price;
        @JsonProperty("price_id")
        public String priceId;
        public PurchaseIntentQuantity quantity;
        public PurchaseIntentUsage usage;
        @JsonProperty("expires_at")
        public String expiresAt;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CreatePurchaseIntentParams params = new CreatePurchaseIntentParams();
            public Builder product(PurchaseIntentProductSelector product) { params.product = product; return this; }
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder price(PurchaseIntentPriceSelector price) { params.price = price; return this; }
            public Builder priceId(String priceId) { params.priceId = priceId; return this; }
            public Builder quantity(PurchaseIntentQuantity quantity) { params.quantity = quantity; return this; }
            public Builder usage(PurchaseIntentUsage usage) { params.usage = usage; return this; }
            public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
            public CreatePurchaseIntentParams build() { return params; }
        }
    }

    public static class LookupPurchaseIntentParams {
        public String id;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupPurchaseIntentParams params = new LookupPurchaseIntentParams();
            public Builder id(String id) { params.id = id; return this; }
            public LookupPurchaseIntentParams build() { return params; }
        }
    }

    public static class PagePurchaseIntentsParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PagePurchaseIntentsParams params = new PagePurchaseIntentsParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public PagePurchaseIntentsParams build() { return params; }
        }
    }

    public static class UpdatePurchaseIntentParams {
        public String id;
        @JsonProperty("minimum_quantity")
        public Integer minimumQuantity;
        @JsonProperty("maximum_quantity")
        public Integer maximumQuantity;
        @JsonProperty("expires_at")
        public String expiresAt;
        public Boolean reactivate;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdatePurchaseIntentParams params = new UpdatePurchaseIntentParams();
            public Builder id(String id) { params.id = id; return this; }
            public Builder minimumQuantity(Integer minimumQuantity) { params.minimumQuantity = minimumQuantity; return this; }
            public Builder maximumQuantity(Integer maximumQuantity) { params.maximumQuantity = maximumQuantity; return this; }
            public Builder expiresAt(String expiresAt) { params.expiresAt = expiresAt; return this; }
            public Builder reactivate(Boolean reactivate) { params.reactivate = reactivate; return this; }
            public UpdatePurchaseIntentParams build() { return params; }
        }
    }

    public static class CancelPurchaseIntentParams {
        public String id;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CancelPurchaseIntentParams params = new CancelPurchaseIntentParams();
            public Builder id(String id) { params.id = id; return this; }
            public CancelPurchaseIntentParams build() { return params; }
        }
    }

    public static class PurchaseIntentActivityAttribution {
        @JsonProperty("landing_url")
        public String landingUrl;
        public String referrer;
        @JsonProperty("referrer_host")
        public String referrerHost;
        public String source;
        public String medium;
        public String campaign;
        public String term;
        public String content;
        public String channel;
    }

    public static class PurchaseIntentActivityVisitor {
        @JsonProperty("session_id")
        public String sessionId;
        @JsonProperty("visitor_id")
        public String visitorId;
        @JsonProperty("user_agent")
        public String userAgent;
        public String device;
        public String browser;
        public String os;
        public String country;
        public String region;
        public String city;
        public String timezone;
    }

    public static class PurchaseIntentActivity {
        public String id;
        @JsonProperty("purchase_intent_id")
        public String purchaseIntentId;
        public String type;
        public String source;
        public PurchaseIntentActivityAttribution attribution;
        public PurchaseIntentActivityVisitor visitor;
        @JsonProperty("product_id")
        public String productId;
        @JsonProperty("variant_product_id")
        public String variantProductId;
        public Integer quantity;
        public Money amount;
        @JsonProperty("order_id")
        public String orderId;
        @JsonProperty("payment_id")
        public String paymentId;
        @JsonProperty("error_code")
        public String errorCode;
        @JsonProperty("created_at")
        public String createdAt;
    }

    public static class PurchaseIntentActivityLog {
        public List<PurchaseIntentActivity> recent;
    }

    public static class PurchaseIntent {
        public String id;
        @JsonProperty("application_id")
        public String applicationId;
        @JsonProperty("product_id")
        public String productId;
        @JsonProperty("price_id")
        public String priceId;
        @JsonProperty("minimum_quantity")
        public Integer minimumQuantity;
        @JsonProperty("maximum_quantity")
        public Integer maximumQuantity;
        @JsonProperty("adjustable_quantity")
        public Boolean adjustableQuantity;
        @JsonProperty("allow_variants")
        public Boolean allowVariants;
        public String status;
        @JsonProperty("created_at")
        public String createdAt;
        @JsonProperty("updated_at")
        public String updatedAt;
        public PurchaseIntentActivityLog activity;
        public ProductModels.Product product;
        public ProductModels.ProductPriceSummary price;
    }

    public static class PurchaseIntentResponse {
        @JsonProperty("purchase_intent")
        public PurchaseIntent purchaseIntent;
        public Object error;
    }

    public static class PurchaseIntentPage {
        public Integer number;
        public Integer size;
        @JsonProperty("purchase_intents")
        public List<PurchaseIntent> purchaseIntents;
    }

    public static class PagePurchaseIntentsResponse {
        public PurchaseIntentPage page;
        public Object error;
    }
}
