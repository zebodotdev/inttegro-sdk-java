package com.inttegro.orders;

import java.util.function.Consumer;

/** A discriminated order line item supplied in a request. */
public class OrderLineItemParams {
    public LineItemType type;
    public ProductLineItemParams product;
    public FeeLineItemParams fee;
    public ShippingLineItemParams shipping;

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final OrderLineItemParams item = new OrderLineItemParams();
        public Builder type(LineItemType type) { item.type = type; return this; }
        public Builder product(ProductLineItemParams product) { item.product = product; return this; }
        public Builder fee(FeeLineItemParams fee) { item.fee = fee; return this; }
        public Builder shipping(ShippingLineItemParams shipping) { item.shipping = shipping; return this; }
        public OrderLineItemParams build() { return item; }
    }

    public static OrderLineItemParams product(ProductLineItemParams product) {
        return builder().type(LineItemType.PRODUCT).product(product).build();
    }

    public static OrderLineItemParams product(Consumer<ProductLineItemParams.Builder> fn) {
        ProductLineItemParams.Builder builder = ProductLineItemParams.builder();
        fn.accept(builder);
        return product(builder.build());
    }

    public static OrderLineItemParams fee(FeeLineItemParams fee) {
        return builder().type(LineItemType.FEE).fee(fee).build();
    }

    public static OrderLineItemParams shipping(ShippingLineItemParams shipping) {
        return builder().type(LineItemType.SHIPPING).shipping(shipping).build();
    }
}
