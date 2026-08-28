package com.zebodotdev.commerce;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zebodotdev.commerce.model.OrderModels.*;
import com.zebodotdev.commerce.model.ProductModels.*;
import com.zebodotdev.commerce.model.AppsModels.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CommerceClientTest {
    private static final Pattern UUID_V7_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-7[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}", Pattern.CASE_INSENSITIVE);

    private HttpServer server;
    private String baseUrl;

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.setExecutor(Executors.newSingleThreadExecutor());
        baseUrl = "http://localhost:" + server.getAddress().getPort();
    }

    @AfterEach
    void stopServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void requestSuccessParsesBody() throws Exception {
        server.createContext("/ping", new JsonHandler(200, "{\"ok\":true}"));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        var resp = client.request("GET", "/ping", null, OkResponse.class);
        assertTrue(resp.ok);
    }

    @Test
    void requestReturnsApiError() throws Exception {
        server.createContext(
                "/ping",
                new JsonHandler(
                        400,
                        "{\"type\":\"invalid_request_parameter\",\"code\":\"invalid_payment_method\",\"url\":\"https://studio.zebo.dev/e/invalid_payment_method\",\"message\":\"missing\",\"detail\":\"Payment method not supported.\",\"fix_code\":\"change_request_parameters\",\"cause\":\"validation_failure\"}"
                )
        );
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        ApiException ex = assertThrows(ApiException.class, () -> client.request("GET", "/ping", null, OkResponse.class));
        assertEquals(400, ex.getStatusCode());
        assertEquals("invalid_payment_method", ex.getCode());
        assertTrue(ex.getMessage().contains("missing"));
    }

    @Test
    void balancesReturnsSnapshot() throws Exception {
        server.createContext("/balances", new JsonHandler(200, "{\"balances\":{\"ghs\":{\"available\":{\"amount\":1000}}}}"));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        var resp = client.balances().get();
        assertEquals(1000L, resp.balances.get("ghs").available.amount);
    }

    @Test
    void payoutsCancelHitsEndpoint() throws Exception {
        server.createContext("/payouts/cancel", new JsonHandler(200, "{\"payout\":{\"id\":\"po_123\",\"status\":\"canceled\"}}"));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        var resp = client.payouts().cancel("po_123");
        assertEquals("po_123", resp.payout.id);
        assertEquals("canceled", resp.payout.status);
    }

    @Test
    void appsEndpointsMatchSpec() throws Exception {
        String appBody = "{\"app\":{\"id\":\"app_123\",\"name\":\"My App\",\"created_at\":\"2026-07-10T00:00:00Z\"}}";
        server.createContext("/apps/create", new JsonHandler(200, appBody));
        server.createContext("/apps/lookup", new JsonHandler(200, appBody));
        server.createContext("/apps/update", new JsonHandler(200, appBody));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        CreateAppResponse created = client.apps().create(
                CreateAppParams.builder().name("My App").build()
        );
        LookupAppResponse lookedUp = client.apps().lookup();
        UpdateAppResponse updated = client.apps().update(
                UpdateAppParams.builder().alias("my-app").build()
        );

        assertEquals("app_123", created.app.id);
        assertEquals("app_123", lookedUp.app.id);
        assertEquals("app_123", updated.app.id);
    }

    @Test
    void orderDocumentDeliveryEndpointsMatchSpec() throws Exception {
        String deliveryBody = "{\"order\":{\"id\":\"or_123\"},\"delivery\":{\"document_kind\":\"invoice\",\"document_url\":\"https://pages.zebo.dev/invoices/or_123\",\"sent_channels\":[\"sms\"]}}";
        server.createContext("/orders/send_invoice", new JsonHandler(200, deliveryBody));
        server.createContext("/orders/send_receipt", new JsonHandler(200, deliveryBody));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);

        OrderDocumentDeliveryResponse invoice = client.orders().sendInvoice(
                OrderSendInvoiceParams.builder()
                        .orderId("or_123")
                        .build()
        );
        assertEquals("or_123", invoice.order.id);
        assertEquals("https://pages.zebo.dev/invoices/or_123", invoice.delivery.documentUrl);

        OrderDocumentDeliveryResponse receipt = client.orders().sendReceipt(
                OrderSendReceiptParams.builder()
                        .orderId("or_123")
                        .build()
        );
        assertEquals("or_123", receipt.order.id);
    }

    @Test
    void mutatingPostsGenerateRequestMetaIdempotencyKey() throws Exception {
        AtomicReference<String> requestBody = new AtomicReference<>();
        server.createContext("/orders/new", exchange -> {
            requestBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
            byte[] bytes = "{\"ok\":true}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        client.request("POST", "/orders/new", Map.of("number", "ORDER-1", "idempotency_key", "legacy"), Map.class);

        assertFalse(requestBody.get().contains("\"idempotency_key\":\"legacy\""));
        assertTrue(requestBody.get().contains("\"request_meta\""));
        assertTrue(UUID_V7_PATTERN.matcher(requestBody.get()).find());
    }

    @Test
    void messageTemplatesCreateUsesRequestMetaIdempotencyByDefault() throws Exception {
        AtomicReference<String> requestBody = new AtomicReference<>();
        AtomicReference<String> idempotencyHeader = new AtomicReference<>();
        server.createContext("/message_templates/create", exchange -> {
            idempotencyHeader.set(exchange.getRequestHeaders().getFirst("Idempotency-Key"));
            requestBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
            byte[] bytes = "{\"ok\":true}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);
        client.messageTemplates().create(Map.of(
                "name", "welcome_sms",
                "channel", "sms",
                "purpose", "marketing",
                "sms", Map.of("message_template", "Welcome {{name}}")
        ));

        assertNull(idempotencyHeader.get());
        assertTrue(requestBody.get().contains("\"request_meta\""));
        assertTrue(UUID_V7_PATTERN.matcher(requestBody.get()).find());
    }

    @Test
    void productsCoverCurrentSpec() throws Exception {
        String productBody = "{\"product\":{\"id\":\"prod_123\",\"default_unit_price\":{\"id\":\"pr_123\",\"nominal\":{\"currency\":\"ghs\",\"value\":5000}}}}";
        server.createContext("/products/create", new JsonHandler(200, productBody));
        server.createContext("/products/add_price", new JsonHandler(200, "{\"price\":{\"id\":\"pr_123\",\"nominal\":{\"currency\":\"ghs\",\"value\":5000}}}"));
        server.createContext("/products/set_default_unit_price", new JsonHandler(200, productBody));
        server.createContext("/products/lookup", new JsonHandler(200, productBody));
        server.createContext("/products/update", new JsonHandler(200, productBody));
        server.createContext("/products/publish", new JsonHandler(200, productBody));
        server.createContext("/products/unpublish", new JsonHandler(200, productBody));
        server.createContext("/products/archive", new JsonHandler(200, productBody));
        server.createContext("/products/page", new JsonHandler(200, "{\"page\":{\"number\":1,\"size\":1,\"products\":[]}}"));
        server.start();

        CommerceClient client = new CommerceClient("sk_test_123", baseUrl, null);

        CreateProductParams create = new CreateProductParams();
        create.type = "physical";
        create.name = "T-Shirt";
        assertEquals("prod_123", client.products().create(create).product.id);

        AddProductPriceParams add = new AddProductPriceParams();
        add.productId = "prod_123";
        add.amount = new ProductPriceAmount();
        add.amount.currency = "ghs";
        add.amount.value = 5000L;
        add.setAsDefault = true;
        assertEquals("pr_123", client.products().addPrice(add).price.id);

        SetDefaultUnitPriceParams setDefault = new SetDefaultUnitPriceParams();
        setDefault.productId = "prod_123";
        setDefault.priceId = "pr_123";
        assertEquals("pr_123", client.products().setDefaultUnitPrice(setDefault).product.defaultUnitPrice.id);

        assertEquals("prod_123", client.products().lookup("prod_123").product.id);
        assertEquals("prod_123", client.products().update(new UpdateProductParams()).product.id);
        assertEquals("prod_123", client.products().publish("prod_123").product.id);
        assertEquals("prod_123", client.products().unpublish("prod_123").product.id);
        assertEquals("prod_123", client.products().archive("prod_123").product.id);

        PageProductsParams page = new PageProductsParams();
        page.pageNumber = 1;
        page.pageSize = 20;
        assertEquals(1, client.products().page(page).page.number);
    }

    private static class OkResponse {
        public boolean ok;
    }

    private static class JsonHandler implements HttpHandler {
        private final int status;
        private final String body;

        JsonHandler(int status, String body) {
            this.status = status;
            this.body = body;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(status, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }
}
