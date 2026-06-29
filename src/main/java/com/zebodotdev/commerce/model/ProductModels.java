package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ProductModels {
    public static class ProductCategory {
        public String id;
        public String name;
        public String slug;
    }

    public static class ProductPrice {
        public Integer amount;
        public String currency;
    }

    public static class ProductPriceAmount {
        public String currency;
        public Long value;
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
    }

    public static class ProductShipment {
        public String type;
        public String carrier;
        public ProductShipmentDimensions dimensions;
    }

    public static class ProductMediaItem {
        public String url;
        public String type;
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
    }

    public static class LookupProductParams {
        @JsonProperty("product_id")
        public String productId;
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
    }

    public static class ProductActionParams {
        @JsonProperty("product_id")
        public String productId;
    }

    public static class AddProductPriceParams {
        @JsonProperty("product_id")
        public String productId;
        public String label;
        public String about;
        public ProductPriceAmount amount;
        @JsonProperty("set_as_default")
        public Boolean setAsDefault;
    }

    public static class SetDefaultUnitPriceParams {
        @JsonProperty("product_id")
        public String productId;
        @JsonProperty("price_id")
        public String priceId;
    }

    public static class PageProductsParams {
        @JsonProperty("page_number")
        public Integer pageNumber;
        @JsonProperty("page_size")
        public Integer pageSize;
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
