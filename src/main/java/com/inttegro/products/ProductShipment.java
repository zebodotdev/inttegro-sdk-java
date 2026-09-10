package com.inttegro.products;

public class ProductShipment {
    public ProductShipmentType type;
    public ProductDelivery delivery;
    public ProductDownload download;
    public ProductRender render;
    public ProductService service;
    public ProductStream stream;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ProductShipment shipment = new ProductShipment();
        public Builder type(ProductShipmentType type) { shipment.type = type; return this; }
        public Builder delivery(ProductDelivery delivery) { shipment.delivery = delivery; return this; }
        public Builder download(ProductDownload download) { shipment.download = download; return this; }
        public Builder render(ProductRender render) { shipment.render = render; return this; }
        public Builder service(ProductService service) { shipment.service = service; return this; }
        public Builder stream(ProductStream stream) { shipment.stream = stream; return this; }
        public ProductShipment build() { return shipment; }
    }
}
