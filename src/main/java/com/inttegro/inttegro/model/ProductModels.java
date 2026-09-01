package com.inttegro.inttegro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ProductModels {
    public static class ProductCategory {
        public String id;
        public String name;
        public String slug;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductCategory category = new ProductCategory();
            public Builder id(String id) { category.id = id; return this; }
            public Builder name(String name) { category.name = name; return this; }
            public Builder slug(String slug) { category.slug = slug; return this; }
            public ProductCategory build() { return category; }
        }
    }

    public static class ProductPrice {
        public Integer amount;
        public String currency;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductPrice price = new ProductPrice();
            public Builder amount(Integer amount) { price.amount = amount; return this; }
            public Builder amount(int amount) { price.amount = amount; return this; }
            public Builder currency(String currency) { price.currency = currency; return this; }
            public ProductPrice build() { return price; }
        }
    }

    public static class ProductPriceAmount {
        public String currency;
        public Long value;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductPriceAmount amount = new ProductPriceAmount();
            public Builder currency(String currency) { amount.currency = currency; return this; }
            public Builder value(Long value) { amount.value = value; return this; }
            public Builder value(long value) { amount.value = value; return this; }
            public ProductPriceAmount build() { return amount; }
        }
    }

    public static class ProductDefaultUnitPrice {
        public String id;
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public ProductPriceAmount nominal;
        @JsonProperty("created_at")
        public String createdAt;
        @JsonProperty("updated_at")
        public String updatedAt;
        @JsonProperty("archived_at")
        public String archivedAt;
    }

    public static class ProductPriceSummary {
        public String id;
        public String label;
        public ProductPriceAmount nominal;
    }

    public static class ProductShipmentDimensions {
        public Double length;
        public Double width;
        public Double height;
        public Double weight;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductShipmentDimensions dimensions = new ProductShipmentDimensions();
            public Builder length(Double length) { dimensions.length = length; return this; }
            public Builder length(double length) { dimensions.length = length; return this; }
            public Builder width(Double width) { dimensions.width = width; return this; }
            public Builder width(double width) { dimensions.width = width; return this; }
            public Builder height(Double height) { dimensions.height = height; return this; }
            public Builder height(double height) { dimensions.height = height; return this; }
            public Builder weight(Double weight) { dimensions.weight = weight; return this; }
            public Builder weight(double weight) { dimensions.weight = weight; return this; }
            public ProductShipmentDimensions build() { return dimensions; }
        }
    }

    public static class ProductShipment {
        public String type;
        public String carrier;
        public ProductShipmentDimensions dimensions;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductShipment shipment = new ProductShipment();
            public Builder type(String type) { shipment.type = type; return this; }
            public Builder carrier(String carrier) { shipment.carrier = carrier; return this; }
            public Builder dimensions(ProductShipmentDimensions dimensions) { shipment.dimensions = dimensions; return this; }
            public ProductShipment build() { return shipment; }
        }
    }

    public static class ProductMediaItem {
        public String url;
        public String type;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductMediaItem media = new ProductMediaItem();
            public Builder url(String url) { media.url = url; return this; }
            public Builder type(String type) { media.type = type; return this; }
            public ProductMediaItem build() { return media; }
        }
    }

    public static class CreateProductParams {
        public String type;
        public String reference;
        public String name;
        public String description;
        public String about;
        @JsonProperty("tax_code")
        public String taxCode;
        public ProductCategory category;
        public ProductPrice price;
        public ProductShipment shipment;
        public ProductMediaItem[] media;
        public Map<String, String> attributes;
        @JsonProperty("custom_data")
        public Map<String, String> customData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final CreateProductParams params = new CreateProductParams();
            public Builder type(String type) { params.type = type; return this; }
            public Builder reference(String reference) { params.reference = reference; return this; }
            public Builder name(String name) { params.name = name; return this; }
            public Builder description(String description) { params.description = description; return this; }
            public Builder about(String about) { params.about = about; return this; }
            public Builder taxCode(String taxCode) { params.taxCode = taxCode; return this; }
            public Builder category(ProductCategory category) { params.category = category; return this; }
            public Builder price(ProductPrice price) { params.price = price; return this; }
            public Builder shipment(ProductShipment shipment) { params.shipment = shipment; return this; }
            public Builder media(ProductMediaItem[] media) { params.media = media; return this; }
            public Builder attributes(Map<String, String> attributes) { params.attributes = attributes; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public CreateProductParams build() { return params; }
        }
    }

    public static class LookupProductParams {
        @JsonProperty("product_id")
        public String productId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final LookupProductParams params = new LookupProductParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public LookupProductParams build() { return params; }
        }
    }

    public static class UpdateProductParams {
        @JsonProperty("product_id")
        public String productId;
        public String reference;
        public String name;
        public String description;
        public String about;
        @JsonProperty("tax_code")
        public String taxCode;
        public ProductCategory category;
        public ProductPrice price;
        public ProductShipment shipment;
        public ProductMediaItem[] media;
        public Map<String, String> attributes;
        @JsonProperty("custom_data")
        public Map<String, String> customData;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final UpdateProductParams params = new UpdateProductParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder reference(String reference) { params.reference = reference; return this; }
            public Builder name(String name) { params.name = name; return this; }
            public Builder description(String description) { params.description = description; return this; }
            public Builder about(String about) { params.about = about; return this; }
            public Builder taxCode(String taxCode) { params.taxCode = taxCode; return this; }
            public Builder category(ProductCategory category) { params.category = category; return this; }
            public Builder price(ProductPrice price) { params.price = price; return this; }
            public Builder shipment(ProductShipment shipment) { params.shipment = shipment; return this; }
            public Builder media(ProductMediaItem[] media) { params.media = media; return this; }
            public Builder attributes(Map<String, String> attributes) { params.attributes = attributes; return this; }
            public Builder customData(Map<String, String> customData) { params.customData = customData; return this; }
            public UpdateProductParams build() { return params; }
        }
    }

    public static class ProductActionParams {
        @JsonProperty("product_id")
        public String productId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final ProductActionParams params = new ProductActionParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public ProductActionParams build() { return params; }
        }
    }

    public static class AddProductPriceParams {
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public ProductPriceAmount amount;
        @JsonProperty("set_as_default")
        public Boolean setAsDefault;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final AddProductPriceParams params = new AddProductPriceParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder label(String label) { params.label = label; return this; }
            public Builder about(String about) { params.about = about; return this; }
            public Builder amount(ProductPriceAmount amount) { params.amount = amount; return this; }
            public Builder setAsDefault(Boolean setAsDefault) { params.setAsDefault = setAsDefault; return this; }
            public Builder setAsDefault(boolean setAsDefault) { params.setAsDefault = setAsDefault; return this; }
            public AddProductPriceParams build() { return params; }
        }
    }

    public static class SetDefaultUnitPriceParams {
        @JsonProperty("product_id")
        public String productId;
        @JsonProperty("price_id")
        public String priceId;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final SetDefaultUnitPriceParams params = new SetDefaultUnitPriceParams();
            public Builder productId(String productId) { params.productId = productId; return this; }
            public Builder priceId(String priceId) { params.priceId = priceId; return this; }
            public SetDefaultUnitPriceParams build() { return params; }
        }
    }

    public static class PageProductsParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private final PageProductsParams params = new PageProductsParams();
            public Builder pageNumber(Integer pageNumber) { params.pageNumber = pageNumber; return this; }
            public Builder pageSize(Integer pageSize) { params.pageSize = pageSize; return this; }
            public PageProductsParams build() { return params; }
        }
    }

    public static class Product {
        public String id;
        @JsonProperty("application_id")
        public String applicationId;
        public String type;
        public String reference;
        public String name;
        public String description;
        public String about;
        @JsonProperty("tax_code")
        public String taxCode;
        public ProductCategory category;
        public ProductPrice price;
        @JsonProperty("default_unit_price")
        public ProductDefaultUnitPrice defaultUnitPrice;
        public List<ProductPriceSummary> prices;
        public ProductShipment shipment;
        public ProductMediaItem[] media;
        public Map<String, String> attributes;
        @JsonProperty("custom_data")
        public Map<String, String> customData;
        public Boolean active;
        public Boolean archived;
        @JsonProperty("created_at")
        public String createdAt;
        @JsonProperty("updated_at")
        public String updatedAt;
        @JsonProperty("archived_at")
        public String archivedAt;
    }

    public static class ProductResponse {
        public Product product;
    }

    public static class AddProductPriceResponse {
        public ProductDefaultUnitPrice price;
    }

    public static class ProductPage {
        public Integer number;
        public Integer size;
        public List<Product> products;
    }

    public static class PageProductsResponse {
        public ProductPage page;
    }
}
