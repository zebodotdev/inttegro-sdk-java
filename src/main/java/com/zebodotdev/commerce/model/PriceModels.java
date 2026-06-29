package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceModels {
    public static class CreatePriceParams {
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public String currency;
        public Long amount;
    }

    public static class LookupPriceParams {
        @JsonProperty("price_id")
        public String priceId;
    }

    public static class UpdatePriceParams {
        @JsonProperty("price_id")
        public String priceId;
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
    }

    public static class PriceNominal {
        public String currency;
        public Long value;
        public Integer sign;
    }

    public static class Price {
        public String id;
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public PriceNominal nominal;
        @JsonProperty("created_at")
        public String createdAt;
        @JsonProperty("updated_at")
        public String updatedAt;
        @JsonProperty("archived_at")
        public String archivedAt;
    }

    public static class PriceResponse {
        public Price price;
    }
}
