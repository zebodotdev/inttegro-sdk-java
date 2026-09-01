package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceModels {
    public static class CreatePriceParams {
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public String currency;
        public Long amount;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CreatePriceParams params = new CreatePriceParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder label(String label) { params.label = label; return this; }
            public Builder about(String about) { params.about = about; return this; }
            public Builder currency(String currency) { params.currency = currency; return this; }
            public Builder amount(Long amount) { params.amount = amount; return this; }
            public Builder amount(long amount) { params.amount = amount; return this; }
            public CreatePriceParams build() { return params; }
        }
    }

    public static class LookupPriceParams {
        @JsonProperty("price_id")
        public String priceId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupPriceParams params = new LookupPriceParams();
            public Builder priceId(String priceId) { params.priceId = priceId; return this; }
            public LookupPriceParams build() { return params; }
        }
    }

    public static class PriceActionParams {
        @JsonProperty("price_id")
        public String priceId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PriceActionParams params = new PriceActionParams();
            public Builder priceId(String priceId) { params.priceId = priceId; return this; }
            public PriceActionParams build() { return params; }
        }
    }

    public static class UpdatePriceParams {
        @JsonProperty("price_id")
        public String priceId;
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdatePriceParams params = new UpdatePriceParams();
            public Builder priceId(String priceId) { params.priceId = priceId; return this; }
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder label(String label) { params.label = label; return this; }
            public Builder about(String about) { params.about = about; return this; }
            public UpdatePriceParams build() { return params; }
        }
    }

    public static class PagePricesParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;
        @JsonProperty("product_id")
        public String productId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PagePricesParams params = new PagePricesParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public Builder productId(String productId) { params.productId = productId; return this; }
            public PagePricesParams build() { return params; }
        }
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

    public static class PricePage {
        public Integer number;
        public Integer size;
        public java.util.List<Price> prices;
    }

    public static class PricePageResponse {
        public PricePage page;
        public Object error;
    }
}
