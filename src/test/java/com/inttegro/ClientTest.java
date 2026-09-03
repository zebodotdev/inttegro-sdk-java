package com.inttegro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inttegro.apps.*;
import com.inttegro.balances.*;
import com.inttegro.common.Money;
import com.inttegro.orders.*;
import com.inttegro.products.*;
import com.inttegro.refunds.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
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

        Client client = new Client("sk_test_123", baseUrl, null);
        var resp = client.request("GET", "/ping", null, OkResponse.class);
        assertTrue(resp.ok);
    }

    @Test
    void requestReturnsApiError() throws Exception {
        server.createContext(
                "/ping",
                new JsonHandler(
                        400,
                        "{\"type\":\"invalid_request_parameter\",\"code\":\"invalid_payment_method\",\"url\":\"https://studio.inttegro.com/e/invalid_payment_method\",\"message\":\"missing\",\"detail\":\"Payment method not supported.\",\"fix_code\":\"change_request_parameters\",\"cause\":\"validation_failure\"}"
                )
        );
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
        ApiException ex = assertThrows(ApiException.class, () -> client.request("GET", "/ping", null, OkResponse.class));
        assertEquals(400, ex.getStatusCode());
        assertEquals("invalid_payment_method", ex.getCode());
        assertTrue(ex.getMessage().contains("missing"));
    }

    @Test
    void balancesReturnsSnapshot() throws Exception {
        server.createContext("/balances", new JsonHandler(200, "{\"balances\":{\"ghs\":{\"available\":{\"amount\":1000}}}}"));
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
        var resp = client.balances().get();
        assertEquals(1000L, resp.get("ghs").available.amount);
    }

    @Test
    void balanceTransactionsDeserializeSemanticSourcesAndOrderEmbedding() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        BalanceTransaction payment = mapper.readValue(
                "{\"id\":\"bt_payment\",\"type\":\"payment\",\"payment_id\":\"py_123\",\"order_id\":\"or_123\",\"amount\":{\"currency\":\"GHS\",\"value\":2500},\"created_at\":\"2026-08-31T12:00:00Z\"}",
                BalanceTransaction.class
        );
        assertEquals(BalanceTransactionType.PAYMENT, payment.type);
        assertEquals("py_123", payment.sourceId());
        assertNull(payment.refundId);
        assertEquals(2500L, payment.amount.value);

        BalanceTransaction refund = mapper.readValue(
                "{\"id\":\"bt_refund\",\"type\":\"refund\",\"refund_id\":\"rf_123\",\"order_id\":\"or_123\",\"amount\":{\"currency\":\"GHS\",\"value\":500},\"created_at\":\"2026-08-31T12:01:00Z\"}",
                BalanceTransaction.class
        );
        assertEquals(BalanceTransactionType.REFUND, refund.type);
        assertEquals("rf_123", refund.sourceId());
        assertNull(refund.paymentId);

        Order order = mapper.readValue(
                "{\"id\":\"or_123\",\"payment\":{\"id\":\"py_123\",\"balance_transaction\":{\"id\":\"bt_payment\",\"type\":\"payment\",\"payment_id\":\"py_123\",\"order_id\":\"or_123\",\"amount\":{\"currency\":\"GHS\",\"value\":2500},\"created_at\":\"2026-08-31T12:00:00Z\"}}}",
                Order.class
        );
        assertEquals(BalanceTransactionType.PAYMENT, order.payment.balanceTransaction.type);
    }

    @Test
    void createPriceSerializesAmountAsMoney() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var params = com.inttegro.prices.CreatePriceParams.builder()
                .productId("prod_123")
                .amount(Money.of("ghs", 2500))
                .build();

        var body = mapper.valueToTree(params);
        assertEquals("ghs", body.get("amount").get("currency").asText());
        assertEquals(2500L, body.get("amount").get("value").asLong());
        assertFalse(body.has("currency"));
    }

    @Test
    void payoutsCancelHitsEndpoint() throws Exception {
        server.createContext("/payouts/cancel", new JsonHandler(200, "{\"payout\":{\"id\":\"po_123\",\"status\":\"canceled\"}}"));
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
        var resp = client.payouts().cancel("po_123");
        assertEquals("po_123", resp.id);
        assertEquals(com.inttegro.payouts.PayoutStatus.CANCELED, resp.status);
    }

    @Test
    void refundsSupportCanonicalLifecycleAndOrderAliasParity() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AtomicReference<String> canonicalBody = new AtomicReference<>();
        AtomicReference<String> aliasBody = new AtomicReference<>();
        String refundBody = "{\"refund\":{\"id\":\"rf_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcd\",\"order_id\":\"or_0123456789abcdefghijklmnopqrstuvwxyzABCD\",\"status\":\"pending\",\"total\":{\"currency\":\"ghs\",\"value\":2500},\"line_items\":[{\"id\":\"rli_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN\",\"order_line_item_id\":\"oli_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN\",\"original_amount_paid\":{\"currency\":\"ghs\",\"value\":5000},\"refund_amount\":{\"currency\":\"ghs\",\"value\":2500}}],\"reason\":\"item_returned\",\"created_at\":\"2026-09-02T10:00:00Z\"}}";
        server.createContext("/refunds/create", exchange -> captureJson(exchange, canonicalBody, refundBody));
        server.createContext("/orders/refund", exchange -> captureJson(exchange, aliasBody, refundBody));
        server.createContext("/refunds/cancel", new JsonHandler(200, refundBody.replace("\"pending\"", "\"canceled\"")));
        server.createContext("/refunds/lookup", new JsonHandler(200, refundBody));
        server.createContext("/refunds/page", new JsonHandler(200, "{\"page\":{\"number\":1,\"refunds\":[],\"size\":0}}"));
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
        CreateRefundParams params = CreateRefundParams.builder()
                .orderId("or_0123456789abcdefghijklmnopqrstuvwxyzABCD")
                .reason(RefundReason.ITEM_RETURNED)
                .reasonDetails("Returned unopened")
                .reference("RETURN-2026-0001")
                .requestMeta(RequestMeta.withIdempotencyKey("refund_contract_001"))
                .lineItem(CreateRefundLineItem.builder()
                        .orderLineItemId("oli_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN")
                        .refundAmount(Money.of("ghs", 2500))
                        .reason(RefundReason.ITEM_NOT_AS_DESCRIBED)
                        .reasonDetails("Wrong size")
                        .build())
                .build();

        Refund canonical = client.refunds().create(params);
        Refund alias = client.orders().refund(params);
        Refund canceled = client.refunds().cancel("rf_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcd");
        Refund lookedUp = client.refunds().lookup("rf_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcd");
        RefundPage page = client.refunds().page(RefundPageParams.builder().pageNumber(1).build());

        assertEquals("rf_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcd", canonical.id);
        assertEquals(RefundStatus.PENDING, canonical.status);
        assertEquals(2500L, canonical.total.value);
        assertEquals(canonical.id, alias.id);
        assertEquals(RefundStatus.CANCELED, canceled.status);
        assertEquals(canonical.id, lookedUp.id);
        assertEquals(0, page.size);
        assertEquals(mapper.readTree(canonicalBody.get()), mapper.readTree(aliasBody.get()));
        assertEquals("item_returned", mapper.readTree(canonicalBody.get()).get("reason").asText());
        assertEquals("oli_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN", mapper.readTree(canonicalBody.get()).get("line_items").get(0).get("order_line_item_id").asText());
    }

    @Test
    void appsEndpointsMatchSpec() throws Exception {
        String appBody = "{\"app\":{\"id\":\"app_123\",\"name\":\"My App\",\"created_at\":\"2026-07-10T00:00:00Z\"}}";
        server.createContext("/apps/create", new JsonHandler(200, appBody));
        server.createContext("/apps/lookup", new JsonHandler(200, appBody));
        server.createContext("/apps/update", new JsonHandler(200, appBody));
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
        App created = client.apps().create(
                CreateAppParams.builder().name("My App").build()
        );
        App lookedUp = client.apps().lookup();
        App updated = client.apps().update(
                UpdateAppParams.builder().alias("my-app").build()
        );

        assertEquals("app_123", created.id);
        assertEquals("app_123", lookedUp.id);
        assertEquals("app_123", updated.id);
    }

    @Test
    void orderDocumentDeliveryEndpointsMatchSpec() throws Exception {
        String deliveryBody = "{\"order\":{\"id\":\"or_123\"},\"delivery\":{\"document_kind\":\"invoice\",\"document_url\":\"https://pages.inttegro.com/invoices/or_123\",\"sent_channels\":[\"sms\"]}}";
        server.createContext("/orders/send_invoice", new JsonHandler(200, deliveryBody));
        server.createContext("/orders/send_receipt", new JsonHandler(200, deliveryBody));
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);

        OrderDocumentDeliveryResult invoice = client.orders().sendInvoice(
                OrderSendInvoiceParams.builder()
                        .orderId("or_123")
                        .build()
        );
        assertEquals("or_123", invoice.order.id);
        assertEquals("https://pages.inttegro.com/invoices/or_123", invoice.delivery.documentUrl);

        OrderDocumentDeliveryResult receipt = client.orders().sendReceipt(
                OrderSendReceiptParams.builder()
                        .orderId("or_123")
                        .build()
        );
        assertEquals("or_123", receipt.order.id);
    }

    @Test
    void ordersReturnDomainModelsInsteadOfTransportEnvelopes() throws Exception {
        server.createContext(
                "/orders/lookup",
                new JsonHandler(200, "{\"order\":{\"id\":\"or_123\",\"status\":\"paid\"}}")
        );
        server.createContext(
                "/orders/page",
                new JsonHandler(200, "{\"page\":{\"number\":0,\"size\":1,\"orders\":[{\"id\":\"or_123\",\"status\":\"paid\"}]}}")
        );
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);

        Order order = client.orders().lookup("or_123");
        OrderPage page = client.orders().page(OrderPageParams.builder().pageSize(1).build());

        assertEquals("or_123", order.id);
        assertEquals("or_123", page.orders.get(0).id);
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

        Client client = new Client("sk_test_123", baseUrl, null);
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
            byte[] bytes = "{\"message_template\":{\"id\":\"mt_123\",\"name\":\"welcome_sms\"}}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        server.start();

        Client client = new Client("sk_test_123", baseUrl, null);
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

        Client client = new Client("sk_test_123", baseUrl, null);

        CreateProductParams create = new CreateProductParams();
        create.type = ProductType.PHYSICAL;
        create.name = "T-Shirt";
        assertEquals("prod_123", client.products().create(create).id);

        AddProductPriceParams add = new AddProductPriceParams();
        add.productId = "prod_123";
        add.amount = new ProductPriceAmount();
        add.amount.currency = "ghs";
        add.amount.value = 5000L;
        add.setAsDefault = true;
        assertEquals("pr_123", client.products().addPrice(add).id);

        SetDefaultUnitPriceParams setDefault = new SetDefaultUnitPriceParams();
        setDefault.productId = "prod_123";
        setDefault.priceId = "pr_123";
        assertEquals("pr_123", client.products().setDefaultUnitPrice(setDefault).defaultUnitPrice.id);

        assertEquals("prod_123", client.products().lookup("prod_123").id);
        assertEquals("prod_123", client.products().update(new UpdateProductParams()).id);
        assertEquals("prod_123", client.products().publish("prod_123").id);
        assertEquals("prod_123", client.products().unpublish("prod_123").id);
        assertEquals("prod_123", client.products().archive("prod_123").id);

        PageProductsParams page = new PageProductsParams();
        page.pageNumber = 1;
        page.pageSize = 20;
        assertEquals(1, client.products().page(page).number);
    }

    private static class OkResponse {
        public boolean ok;
    }

    private static void captureJson(HttpExchange exchange, AtomicReference<String> target, String responseBody) throws IOException {
        target.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
        byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
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
