package com.inttegro.purchaseintents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.Money;
import java.util.List;

public class PurchaseIntentActivity {
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
