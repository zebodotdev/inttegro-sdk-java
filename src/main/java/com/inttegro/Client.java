package com.inttegro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inttegro.apps.*;
import com.inttegro.balances.*;
import com.inttegro.broadcasts.*;
import com.inttegro.chimes.*;
import com.inttegro.common.*;
import com.inttegro.customers.*;
import com.inttegro.filereferences.*;
import com.inttegro.files.*;
import com.inttegro.financialaccounts.*;
import com.inttegro.keys.*;
import com.inttegro.messages.*;
import com.inttegro.otp.*;
import com.inttegro.orders.*;
import com.inttegro.paymentmethods.*;
import com.inttegro.payouts.*;
import com.inttegro.prices.*;
import com.inttegro.products.*;
import com.inttegro.purchaseintents.*;
import com.inttegro.refunds.*;
import com.inttegro.schedules.*;
import com.inttegro.specifications.*;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Main entry point for the Inttegro HTTP API.
 *
     * <p>Usage (mirrors studio samples):
     * <pre>{@code
     * Client client = new Client(System.getenv("INTTEGRO_API_KEY"));
     * var created = client.orders().create(
     *     OrderCreateParams.builder()
     *       .customerData(CustomerData.builder().name("Akua").phoneNumber("+233...").build())
     *       .paymentMethodData(PaymentMethodData.mobileMoney(m -> m.network("mtn").accountNumber("0544...")))
     *       .lineItem(OrderLineItem.product(p -> p.name("Subscription").type("digital").price(Money.of("ghs", 5000)).quantity(1)))
     *       .billingDetails(BillingDetails.builder().name("Akua").phoneNumber("+233...").build())
     *       .executePayment(true)
     *       .build()
     * );
     * }</pre>
     *
     * Transport: JDK {@link java.net.http.HttpClient} (no external HTTP deps) with Jackson for JSON.<br/>
     * Auth: {@code Authorization: Bearer <key>} on every request.<br/>
     * Timeout: 30s default; supply your own HttpClient to change.<br/>
     * Thread safety: immutable after construction; share freely across goroutines/threads.
     */
public class Client {
    public static final String VERSION = "3.0.1";

    private static final String DEFAULT_BASE_URL = "https://api.inttegro.com";
    private static final String USER_AGENT = "inttegro-sdk-java/" + VERSION;
    private static final Set<String> NON_IDEMPOTENT_POST_ACTIONS = Set.of(
            "lookup", "page", "settings", "countries", "contents", "balances", "render_preview"
    );

    private final String apiKey;
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    private final SecureRandom random;

    public final OrdersClient orders;
    public final RefundsClient refunds;
    public final ChimesClient chimes;
    public final SchedulesClient schedules;
    public final BroadcastsClient broadcasts;
    public final OtpClient otp;
    public final PaymentMethodsClient paymentMethods;
    public final PayoutsClient payouts;
    public final BalanceTransactionsClient balanceTransactions;
    public final FinancialAccountsClient financialAccounts;
    public final FilesClient files;
    public final FileReferencesClient fileReferences;
    public final FileLinksClient fileLinks;
    public final UploadRequestsClient uploadRequests;
    public final MessageTemplatesClient messageTemplates;
    public final CustomersClient customers;
    public final ProductsClient products;
    public final PricesClient prices;
    public final SpecClient spec;
    public final AppsClient apps;
    public final KeysClient keys;
    public final BalancesClient balances;
    public final PurchaseIntentsClient purchaseIntents;

    /**
     * Create a client with the default production base URL.
     *
     * @param apiKey secret key from the dashboard (e.g. sk_test_... or sk_live_...)
     */
    public Client(String apiKey) {
        this(apiKey, DEFAULT_BASE_URL, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build());
    }

    /**
     * Create a client with a custom base URL and/or HttpClient.
     *
     * <p>Useful for pointing to a mock server in tests or a staging environment. Provide a custom
     * HttpClient if you need proxy support, custom timeouts, or a shared connection pool.</p>
     *
     * @param apiKey secret key
     * @param baseUrl override API host (e.g., https://staging-api.inttegro.com)
     * @param httpClient custom HttpClient (optional; pass null to use default)
     */
    public Client(String apiKey, String baseUrl, HttpClient httpClient) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("apiKey is required");
        }
        this.apiKey = apiKey;
        this.baseUrl = baseUrl != null && !baseUrl.isEmpty() ? baseUrl : DEFAULT_BASE_URL;
        this.httpClient = httpClient != null ? httpClient : HttpClient.newHttpClient();
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.random = new SecureRandom();

        this.orders = new OrdersClient(this);
        this.refunds = new RefundsClient(this);
        this.chimes = new ChimesClient(this);
        this.schedules = new SchedulesClient(this);
        this.broadcasts = new BroadcastsClient(this);
        this.otp = new OtpClient(this);
        this.paymentMethods = new PaymentMethodsClient(this);
        this.payouts = new PayoutsClient(this);
        this.balanceTransactions = new BalanceTransactionsClient(this);
        this.financialAccounts = new FinancialAccountsClient(this);
        this.files = new FilesClient(this);
        this.fileReferences = new FileReferencesClient(this);
        this.fileLinks = new FileLinksClient(this);
        this.uploadRequests = new UploadRequestsClient(this);
        this.messageTemplates = new MessageTemplatesClient(this);
        this.customers = new CustomersClient(this);
        this.products = new ProductsClient(this);
        this.prices = new PricesClient(this);
        this.spec = new SpecClient(this);
        this.apps = new AppsClient(this);
        this.keys = new KeysClient(this);
        this.balances = new BalancesClient(this);
        this.purchaseIntents = new PurchaseIntentsClient(this);
    }

    // Convenience accessors matching doc samples.

    /**
     * Access the Orders resource for creating, paying, finalizing, and managing orders.
     * Orders represent complete customer transactions from cart to payment to fulfillment.
     *
     * @return orders client for order-related operations
     */
    public OrdersClient orders() { return orders; }

    /** Access canonical refund creation, cancellation, lookup, and pagination operations. */
    public RefundsClient refunds() { return refunds; }

    /**
     * Access the Schedules resource for scheduled chime lookups and cancellations.
     *
     * @return schedules client for schedule-related operations
     */
    public SchedulesClient schedules() { return schedules; }

    /**
     * Access the Broadcasts resource for broadcast lookups and cancellations.
     *
     * @return broadcasts client for broadcast-related operations
     */
    public BroadcastsClient broadcasts() { return broadcasts; }

    /**
     * Access the Chimes resource for sending and scheduling notification messages.
     * Chimes are delivered via SMS or email and can be sent immediately or scheduled for later.
     *
     * @return chimes client for notification operations
     */
    public ChimesClient chimes() { return chimes; }

    /**
     * Access the OTP resource for initializing and verifying one-time passwords.
     * OTP sessions provide secure verification codes for authentication flows.
     *
     * @return OTP client for one-time password operations
     */
    public OtpClient otp() { return otp; }

    /**
     * Access the Payment Methods resource for tokenizing, verifying, and managing saved payment methods.
     * Payment methods can be reused across orders without requiring customers to re-enter details.
     *
     * @return payment methods client for payment method operations
     */
    public PaymentMethodsClient paymentMethods() { return paymentMethods; }

    /**
     * Access the Payouts resource for configuring payout settings and retrieving payout history.
     * Payouts transfer funds from your Inttegro balance to your bank accounts or mobile money.
     *
     * @return payouts client for payout operations
     */
    public PayoutsClient payouts() { return payouts; }

    /**
     * Access the Balance Transactions resource for retrieving transaction history.
     * Balance transactions show all changes to your Inttegro balance including charges, refunds, and fees.
     *
     * @return balance transactions client for balance transaction operations
     */
    public BalanceTransactionsClient balanceTransactions() { return balanceTransactions; }

    /**
     * Access the Balances resource for retrieving balance snapshots.
     * Balances show available/pending/reserved/refund amounts per currency.
     *
     * @return balances client for balance retrieval
     */
    public BalancesClient balances() { return balances; }

    /**
     * Access the Financial Accounts resource for managing bank accounts and mobile money accounts.
     * Financial accounts serve as payout destinations for receiving funds from your balance.
     *
     * @return financial accounts client for account operations
     */
    public FinancialAccountsClient financialAccounts() { return financialAccounts; }
    public FilesClient files() { return files; }
    public FileReferencesClient fileReferences() { return fileReferences; }
    public FileLinksClient fileLinks() { return fileLinks; }
    public UploadRequestsClient uploadRequests() { return uploadRequests; }
    public MessageTemplatesClient messageTemplates() { return messageTemplates; }
    public CustomersClient customers() { return customers; }
    public PricesClient prices() { return prices; }
    public ProductsClient products() { return products; }
    public PurchaseIntentsClient purchaseIntents() { return purchaseIntents; }

    /**
     * Access the Spec resource for retrieving country specifications and payment capabilities.
     * Specifications define supported payment methods, currencies, and requirements per country.
     *
     * @return spec client for specification operations
     */
    public SpecClient spec() { return spec; }

    /** Access application creation, lookup, and update operations. */
    public AppsClient apps() { return apps; }
    public KeysClient keys() { return keys; }

    String getBaseUrl() {
        return baseUrl;
    }

    ObjectMapper getMapper() {
        return mapper;
    }

    HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Performs an HTTP request with optional JSON body.
     */
    <T> T request(String method, String path, Object body, Class<T> responseClass) throws IOException, InterruptedException, ApiException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .timeout(Duration.ofSeconds(30))
                .header("Authorization", "Bearer " + apiKey)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json");

        Object requestBody = body != null ? bodyWithIdempotency(method, path, body, null) : null;
        if (requestBody != null) {
            builder.header("Content-Type", "application/json");
            builder.method(method, HttpRequest.BodyPublishers.ofString(serialize(requestBody), StandardCharsets.UTF_8));
        } else {
            builder.method(method, HttpRequest.BodyPublishers.noBody());
        }

        HttpResponse<String> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (response.statusCode() >= 400) {
            throw parseApiException(response);
        }

        if (responseClass == null || response.body() == null || response.body().isEmpty()) {
            return null;
        }
        return mapper.readValue(response.body(), responseClass);
    }

    <T> T requestWithOptions(String method, String path, Object body, RequestOptions options, Class<T> responseClass) throws IOException, InterruptedException, ApiException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .timeout(Duration.ofSeconds(30))
                .header("Authorization", "Bearer " + apiKey)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json");
        String explicitIdempotencyKey = options != null ? options.idempotencyKey() : null;
        if (explicitIdempotencyKey != null && !explicitIdempotencyKey.isBlank()) {
            builder.header("Idempotency-Key", explicitIdempotencyKey);
        }
        Object requestBody = body != null ? bodyWithIdempotency(method, path, body, explicitIdempotencyKey) : null;
        if (requestBody != null) {
            builder.header("Content-Type", "application/json");
            builder.method(method, HttpRequest.BodyPublishers.ofString(serialize(requestBody), StandardCharsets.UTF_8));
        } else {
            builder.method(method, HttpRequest.BodyPublishers.noBody());
        }
        HttpResponse<String> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() >= 400) {
            throw parseApiException(response);
        }
        return mapper.readValue(response.body(), responseClass);
    }

    private <T> T requestResource(String path, Object body, String field, Class<T> resourceClass)
            throws IOException, InterruptedException, ApiException {
        JsonNode envelope = request("POST", path, body, JsonNode.class);
        return decodeResource(envelope, field, resourceClass);
    }

    private <T> T requestResourceWithOptions(
            String path,
            Object body,
            RequestOptions options,
            String field,
            Class<T> resourceClass
    ) throws IOException, InterruptedException, ApiException {
        JsonNode envelope = requestWithOptions("POST", path, body, options, JsonNode.class);
        return decodeResource(envelope, field, resourceClass);
    }

    private <T> T decodeResource(JsonNode envelope, String field, Class<T> resourceClass)
            throws JsonProcessingException {
        JsonNode resource = envelope.get(field);
        if (resource == null || resource.isNull()) {
            throw new IllegalStateException("Inttegro returned an invalid " + field + " value");
        }
        return mapper.treeToValue(resource, resourceClass);
    }

    private String serialize(Object body) throws JsonProcessingException {
        return mapper.writeValueAsString(body);
    }

    private Map<String, Object> bodyWithIdempotency(String method, String path, Object body, String explicitIdempotencyKey) {
        Map<String, Object> payload = toRequestMap(body);
        payload.remove("idempotency_key");
        if (!"POST".equalsIgnoreCase(method) || !isIdempotentMutationPath(path)) {
            return payload;
        }
        if (explicitIdempotencyKey != null && !explicitIdempotencyKey.isBlank()) {
            return payload;
        }

        Map<String, Object> requestMeta = mapValue(payload.get("request_meta"));
        Object existing = requestMeta.get("idempotency_key");
        if (existing == null || existing.toString().isBlank()) {
            requestMeta.put("idempotency_key", generateIdempotencyKey());
        }
        payload.put("request_meta", requestMeta);
        return payload;
    }

    private Map<String, Object> mapValue(Object value) {
        Map<String, Object> result = new HashMap<>();
        if (value instanceof Map<?, ?> map) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                result.put(entry.getKey().toString(), entry.getValue());
            }
        }
        return result;
    }

    private boolean isIdempotentMutationPath(String pathOrUrl) {
        String path = pathOrUrl;
        if (pathOrUrl.startsWith("http://") || pathOrUrl.startsWith("https://")) {
            try {
                path = URI.create(pathOrUrl).getPath();
            } catch (IllegalArgumentException ignored) {
                return false;
            }
        }
        if (path.startsWith("/keys/")) {
            return false;
        }
        String[] parts = path.replaceAll("^/+|/+$", "").split("/");
        if (parts.length == 0 || parts[parts.length - 1].isEmpty()) {
            return false;
        }
        return !NON_IDEMPOTENT_POST_ACTIONS.contains(parts[parts.length - 1]);
    }

    private String generateIdempotencyKey() {
        byte[] bytes = new byte[16];
        long timestamp = System.currentTimeMillis() & 0xffffffffffffL;
        bytes[0] = (byte) (timestamp >>> 40);
        bytes[1] = (byte) (timestamp >>> 32);
        bytes[2] = (byte) (timestamp >>> 24);
        bytes[3] = (byte) (timestamp >>> 16);
        bytes[4] = (byte) (timestamp >>> 8);
        bytes[5] = (byte) timestamp;
        byte[] randomBytes = new byte[10];
        random.nextBytes(randomBytes);
        System.arraycopy(randomBytes, 0, bytes, 6, randomBytes.length);
        bytes[6] = (byte) ((bytes[6] & 0x0f) | 0x70);
        bytes[8] = (byte) ((bytes[8] & 0x3f) | 0x80);
        StringBuilder hex = new StringBuilder(32);
        for (byte b : bytes) {
            hex.append(String.format("%02x", b & 0xff));
        }
        return hex.substring(0, 8) + "-" + hex.substring(8, 12) + "-" + hex.substring(12, 16) + "-" + hex.substring(16, 20) + "-" + hex.substring(20);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> toRequestMap(Object params) {
        if (params == null) {
            return new HashMap<>();
        }
        Map<String, Object> fields;
        if (params instanceof Map<?, ?> map) {
            fields = new HashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                fields.put(entry.getKey().toString(), mutableValue(entry.getValue()));
            }
        } else {
            fields = mapper.convertValue(params, Map.class);
        }
        pruneNulls(fields);
        return fields;
    }

    private static Object mutableValue(Object value) {
        if (value instanceof Map<?, ?> map) {
            Map<String, Object> copy = new HashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                copy.put(entry.getKey().toString(), mutableValue(entry.getValue()));
            }
            return copy;
        }
        if (value instanceof List<?> list) {
            List<Object> copy = new ArrayList<>();
            for (Object item : list) {
                copy.add(mutableValue(item));
            }
            return copy;
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private static void pruneNulls(Object value) {
        if (value instanceof Map<?, ?> map) {
            ((Map<Object, Object>) map).entrySet().removeIf(entry -> {
                Object entryValue = entry.getValue();
                if (entryValue == null) {
                    return true;
                }
                pruneNulls(entryValue);
                return false;
            });
        } else if (value instanceof List<?> list) {
            for (Object item : list) {
                pruneNulls(item);
            }
        }
    }

    private ApiException parseApiException(HttpResponse<String> response) {
        try {
            Map<?, ?> env = mapper.readValue(response.body(), Map.class);
            Object errObj = env.get("error");
            Map<?, ?> payload = errObj instanceof Map<?, ?> ? (Map<?, ?>) errObj : env;
            String code = stringVal(payload.get("code"));
            String type = stringVal(payload.get("type"));
            String url = stringVal(payload.get("url"));
            String message = stringVal(payload.get("message"));
            String detail = stringVal(payload.get("detail"));
            String fixCode = stringVal(payload.get("fix_code"));
            String cause = stringVal(payload.get("cause"));
            if (code != null || type != null || url != null || message != null || detail != null) {
                return new ApiException(response.statusCode(), code, type, url, message, detail, fixCode, cause);
            }
        } catch (Exception ignored) {
        }
        return new ApiException(response.statusCode(), null, null, null, response.body(), null, null, null);
    }

    private String stringVal(Object o) {
        return o != null ? o.toString() : null;
    }

    private JsonNode multipartRequest(String pathOrUrl, Map<String, Object> fields, Map<String, Path> files, RequestOptions options, boolean authenticated) throws IOException, InterruptedException, ApiException {
        String boundary = "----InttegroBoundary" + UUID.randomUUID();
        byte[] body = multipartBody(boundary, fields, files);
        String url = pathOrUrl.startsWith("http://") || pathOrUrl.startsWith("https://") ? pathOrUrl : baseUrl + pathOrUrl;
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Accept", "application/json")
                .header("User-Agent", USER_AGENT)
                .header("Content-Type", "multipart/form-data; boundary=" + boundary);
        if (authenticated) {
            builder.header("Authorization", "Bearer " + apiKey);
        }
        String explicitIdempotencyKey = options != null ? options.idempotencyKey() : null;
        if (explicitIdempotencyKey != null && !explicitIdempotencyKey.isBlank()) {
            builder.header("Idempotency-Key", explicitIdempotencyKey);
        } else if (authenticated && isIdempotentMutationPath(pathOrUrl)) {
            builder.header("Idempotency-Key", generateIdempotencyKey());
        }
        HttpResponse<String> response = httpClient.send(
                builder.POST(HttpRequest.BodyPublishers.ofByteArray(body)).build(),
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );
        if (response.statusCode() >= 400) {
            throw parseApiException(response);
        }
        return mapper.readTree(response.body());
    }

    private <T> T multipartResource(
            String pathOrUrl,
            Map<String, Object> fields,
            Map<String, Path> files,
            RequestOptions options,
            boolean authenticated,
            String field,
            Class<T> resourceClass
    ) throws IOException, InterruptedException, ApiException {
        return decodeResource(multipartRequest(pathOrUrl, fields, files, options, authenticated), field, resourceClass);
    }

    private byte[] multipartBody(String boundary, Map<String, Object> fields, Map<String, Path> files) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (entry.getValue() == null) continue;
            out.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
            out.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
            Object value = entry.getValue();
            out.write((value instanceof Map<?, ?> || value instanceof List<?> ? serialize(value) : value.toString()).getBytes(StandardCharsets.UTF_8));
            out.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }
        for (Map.Entry<String, Path> entry : files.entrySet()) {
            Path path = entry.getValue();
            String contentType = Files.probeContentType(path);
            if (contentType == null) contentType = "application/octet-stream";
            out.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
            out.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"; filename=\"" + path.getFileName() + "\"\r\n").getBytes(StandardCharsets.UTF_8));
            out.write(("Content-Type: " + contentType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
            out.write(Files.readAllBytes(path));
            out.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }
        out.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
        return out.toByteArray();
    }

    private FileDownload binaryRequest(String method, String pathOrUrl, Object body, boolean authenticated) throws IOException, InterruptedException, ApiException {
        String url = pathOrUrl.startsWith("http://") || pathOrUrl.startsWith("https://") ? pathOrUrl : baseUrl + pathOrUrl;
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("User-Agent", USER_AGENT);
        if (authenticated) {
            builder.header("Authorization", "Bearer " + apiKey);
        }
        if (body != null) {
            builder.header("Content-Type", "application/json");
            builder.method(method, HttpRequest.BodyPublishers.ofString(serialize(body), StandardCharsets.UTF_8));
        } else {
            builder.method(method, HttpRequest.BodyPublishers.noBody());
        }
        HttpResponse<byte[]> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());
        if (response.statusCode() >= 400) {
            throw new ApiException(response.statusCode(), null, null, null, new String(response.body(), StandardCharsets.UTF_8), null, null, null);
        }
        return new FileDownload(response.body());
    }

    // ------------ Resource clients -------------

    public static class FilesClient {
        private final Client client;

        public FilesClient(Client client) {
            this.client = client;
        }

        public StoredFile create(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public StoredFile create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            Object file = fields.remove("file");
            fields.remove("idempotency_key");
            return client.multipartResource("/files/create", fields, Map.of("file", toPath(file)), options, true, "file", StoredFile.class);
        }

        public StoredFile create(FileCreateParams params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public StoredFile create(FileCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            Object file = fields.remove("file");
            return client.multipartResource("/files/create", fields, Map.of("file", toPath(file)), options, true, "file", StoredFile.class);
        }

        public StoredFile lookup(String fileId) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/files/lookup", Map.of("file_id", fileId), "file", StoredFile.class);
        }

        public StoredFilePage page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/files/page", params, "page", StoredFilePage.class);
        }

        public StoredFilePage page(FilePageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/files/page", client.toRequestMap(params), "page", StoredFilePage.class);
        }

        public FileDownload contents(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("POST", "/files/contents", params, true);
        }

        public FileDownload contents(FileContentsParams params) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("POST", "/files/contents", client.toRequestMap(params), true);
        }

        public StoredFile delete(String fileId) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/files/delete", Map.of("file_id", fileId), "file", StoredFile.class);
        }
    }

    public static class FileLinksClient {
        private final Client client;

        public FileLinksClient(Client client) {
            this.client = client;
        }

        public FileLinkCreation create(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public FileLinkCreation create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/create", params, options, FileLinkCreation.class);
        }

        public FileLinkCreation create(FileLinkCreateParams params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public FileLinkCreation create(FileLinkCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/create", client.toRequestMap(params), options, FileLinkCreation.class);
        }

        public FileLink lookup(String id) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/file_links/lookup", Map.of("id", id), "file_link", FileLink.class);
        }

        public FileLinkPage page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/file_links/page", params, "page", FileLinkPage.class);
        }

        public FileLinkPage page(FileLinkPageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/file_links/page", client.toRequestMap(params), "page", FileLinkPage.class);
        }

        public FileLink revoke(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/file_links/revoke", params, options, "file_link", FileLink.class);
        }

        public FileLink revoke(FileLinkRevokeParams params) throws IOException, InterruptedException, ApiException {
            return revoke(params, null);
        }

        public FileLink revoke(FileLinkRevokeParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/file_links/revoke", client.toRequestMap(params), options, "file_link", FileLink.class);
        }

        public FileDownload open(String url) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("GET", url, null, false);
        }
    }

    public static class FileReferencesClient {
        private final Client client;

        public FileReferencesClient(Client client) {
            this.client = client;
        }

        public FileReferenceReconciliation reconcile(FileReferenceReconcileParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/file_references/reconcile", params, FileReferenceReconciliation.class);
        }

        public FileReferenceReconciliation reconcile(FileReferenceReconcileParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_references/reconcile", params, options, FileReferenceReconciliation.class);
        }
    }

    public static class UploadRequestsClient {
        private final Client client;

        public UploadRequestsClient(Client client) {
            this.client = client;
        }

        public UploadRequest create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/create", params, options, "upload_request", UploadRequest.class);
        }

        public UploadRequest create(UploadRequestCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/create", client.toRequestMap(params), options, "upload_request", UploadRequest.class);
        }

        public UploadRequest lookup(String id) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/upload_requests/lookup", Map.of("id", id), "upload_request", UploadRequest.class);
        }

        public UploadRequestPage page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/upload_requests/page", params, "page", UploadRequestPage.class);
        }

        public UploadRequestPage page(UploadRequestPageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/upload_requests/page", client.toRequestMap(params), "page", UploadRequestPage.class);
        }

        public UploadRequest cancel(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/cancel", params, options, "upload_request", UploadRequest.class);
        }

        public UploadRequest cancel(UploadRequestCancelParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/cancel", client.toRequestMap(params), options, "upload_request", UploadRequest.class);
        }

        public UploadRequest review(ReviewUploadRequestAttemptByIdParams params) throws IOException, InterruptedException, ApiException {
            return review(params, null);
        }

        public UploadRequest review(ReviewUploadRequestAttemptByIdParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/review", client.toRequestMap(params), options, "upload_request", UploadRequest.class);
        }

        public UploadRequest review(ReviewUploadRequestAttemptByOrdinalParams params) throws IOException, InterruptedException, ApiException {
            return review(params, null);
        }

        public UploadRequest review(ReviewUploadRequestAttemptByOrdinalParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/upload_requests/review", client.toRequestMap(params), options, "upload_request", UploadRequest.class);
        }

        public UploadFulfillment fulfill(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            String uploadURL = (String) params.get("upload_url");
            return client.mapper.treeToValue(client.multipartRequest(uploadURL, Map.of(), Map.of("file", toPath(params.get("file"))), null, false), UploadFulfillment.class);
        }

        public UploadFulfillment fulfill(UploadRequestFulfillParams params) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            String uploadURL = (String) fields.get("upload_url");
            return client.mapper.treeToValue(client.multipartRequest(uploadURL, Map.of(), Map.of("file", toPath(fields.get("file"))), null, false), UploadFulfillment.class);
        }
    }

    public static class MessageTemplatesClient {
        private final Client client;

        public MessageTemplatesClient(Client client) {
            this.client = client;
        }

        public MessageTemplate create(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public MessageTemplate create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/message_templates/create", params, options, "message_template", MessageTemplate.class);
        }

        public MessageTemplate update(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return update(params, null);
        }

        public MessageTemplate update(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/message_templates/update", params, options, "message_template", MessageTemplate.class);
        }

        public MessageTemplate publish(String templateId) throws IOException, InterruptedException, ApiException {
            return publish(templateId, null);
        }

        public MessageTemplate publish(String templateId, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/message_templates/publish", Map.of("id", templateId), options, "message_template", MessageTemplate.class);
        }

        public MessageTemplate archive(String templateId) throws IOException, InterruptedException, ApiException {
            return archive(templateId, null);
        }

        public MessageTemplate archive(String templateId, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/message_templates/archive", Map.of("id", templateId), options, "message_template", MessageTemplate.class);
        }

        public MessageTemplate lookup(String templateId) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/message_templates/lookup", Map.of("id", templateId), "message_template", MessageTemplate.class);
        }

        public MessageTemplatePage page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/message_templates/page", params, "page", MessageTemplatePage.class);
        }

        public MessageTemplatePreview renderPreview(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/message_templates/render_preview", params, MessageTemplatePreview.class);
        }

    }

    private static Path toPath(Object value) {
        if (value instanceof Path path) {
            return path;
        }
        return Path.of(value.toString());
    }

    public static class OrdersClient {
        private final Client client;

        public OrdersClient(Client client) {
            this.client = client;
        }

        /**
         * Creates a new order (POST /orders/new).
         *
         * <p>Creates a new order in the Inttegro platform. Supports two flows:</p>
         * <ol>
         *   <li>New customer flow: Provide {@code customer_data} to create a new customer and order</li>
         *   <li>Existing customer flow: Provide {@code customer_id} and optionally {@code payment_method_id} for known customers</li>
         * </ol>
         *
         * <p>The order can be configured to execute payment immediately or require manual payment later.
         * Set {@code execute_payment=true} to charge immediately. Include {@code checkout_settings} with
         * redirect and cancel URLs for hosted checkout flows. Optionally set {@code finalize=true} to seal
         * the order immediately.</p>
         *
         * @param params order creation parameters including customer info, line items, and payment settings
         * @return the created order
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the API returns an error (400, 401, 422)
         */
        public Order create(OrderCreateParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/create", params);
        }

        public Order newOrder(OrderCreateParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/new", params);
        }

        /**
         * Retrieves details of an existing order by its ID (POST /orders/lookup).
         *
         * <p>Returns full order details including line items, payment status, customer information,
         * invoice URLs, and all timestamps. The order may or may not exist.</p>
         *
         * @param orderId unique identifier of the order to lookup
         * @return the order details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404) or other API errors occur
         */
        public Order lookup(String orderId) throws IOException, InterruptedException, ApiException {
            OrderLookupParams p = new OrderLookupParams();
            p.orderId = orderId;
            return requestOrder("/orders/lookup", p);
        }

        public Order update(OrderUpdateParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/update", params);
        }

        /**
         * Initiates payment for an existing order (POST /orders/pay).
         *
         * <p>Supports two payment flows:</p>
         * <ol>
         *   <li>Use saved payment method: Provide only {@code order_id} to charge a previously saved payment method</li>
         *   <li>Provide new payment details: Include {@code payment_method_data} with payment information (e.g., mobile money network and account number)</li>
         * </ol>
         *
         * <p>For out-of-band payments (cash, bank transfer, check), set {@code paid_out_of_band=true} to mark
         * the payment as paid offline. This is mutually exclusive with {@code payment_method_id} and {@code payment_method_data}.</p>
         *
         * @param params payment parameters including order ID and payment method information
         * @return the updated order, including payment and next-action state
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), already paid, or other API errors occur
         */
        public Order pay(OrderPayParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/pay", params);
        }

        /**
         * Confirms a pending payment using a verification token (POST /orders/confirm_payment).
         *
         * <p>Use this endpoint when a payment requires confirmation (OTP verification). The payment response
         * will indicate {@code requires_confirmation=true} when a token has been sent to the customer's phone
         * or email. Submit the token the customer provides to complete the payment.</p>
         *
         * @param params confirmation parameters including order ID and verification token (OTP)
         * @return the updated order with confirmed payment status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the token is invalid (400), order not found (404), or other API errors occur
         */
        public Order confirmPayment(OrderConfirmParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/confirm_payment", params);
        }

        /**
         * Requests a new confirmation token to be sent to the customer (POST /orders/request_confirmation).
         *
         * <p>Triggers delivery of a new payment verification token (OTP) to the customer. Use this when
         * the customer didn't receive the initial token or needs a replacement. The token will be sent
         * via the same channel (SMS or email) as the original.</p>
         *
         * @param orderId unique identifier of the order requiring confirmation
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404) or doesn't require confirmation
         */
        public Order requestConfirmation(String orderId) throws IOException, InterruptedException, ApiException {
            OrderRequestConfirmationParams p = new OrderRequestConfirmationParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("request_confirmation", orderId);
            return requestConfirmation(p);
        }

        public Order requestConfirmation(OrderRequestConfirmationParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/request_confirmation", params);
        }

        /**
         * Finalizes an order to make it ready for payment (POST /orders/finalize).
         *
         * <p>Once finalized, the order becomes immutable—line items, totals, and customer data can no longer
         * be changed. This endpoint generates an invoice and hosted checkout page that you can share with
         * customers. The response includes invoice URLs and the {@code sealed_at} timestamp marking when
         * the order was finalized.</p>
         *
         * <p>Use this when you've finished building the cart and want to present it to the customer for payment.</p>
         *
         * @param orderId unique identifier of the order to finalize
         * @return the finalized order with invoice and checkout URLs
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), already finalized, or cannot be finalized (422)
         */
        public Order finalizeOrder(String orderId) throws IOException, InterruptedException, ApiException {
            OrderFinalizeParams p = new OrderFinalizeParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("finalize", orderId);
            return finalizeOrder(p);
        }

        public Order finalizeOrder(OrderFinalizeParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/finalize", params);
        }

        /**
         * Sends the hosted invoice link for an existing order (POST /orders/send_invoice).
         *
         * @param params send invoice parameters including order ID
         * @return the order document delivery result
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found or delivery fails
         */
        public OrderDocumentDeliveryResult sendInvoice(OrderSendInvoiceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/send_invoice", params, OrderDocumentDeliveryResult.class);
        }

        /**
         * Sends the hosted receipt link for a paid order (POST /orders/send_receipt).
         *
         * @param params send receipt parameters including order ID
         * @return the order document delivery result
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found, unpaid, or delivery fails
         */
        public OrderDocumentDeliveryResult sendReceipt(OrderSendReceiptParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/send_receipt", params, OrderDocumentDeliveryResult.class);
        }

        /**
         * Marks an order as completed (POST /orders/complete).
         *
         * <p>Transitions the order to the {@code completed} state. An order can only be completed if its
         * associated payment has been successfully paid. Once completed, the order status becomes {@code completed}
         * and the {@code completed_at} timestamp is set.</p>
         *
         * <p>Use the {@code paid_out_of_band} parameter when payment happened offline (outside the Inttegro
         * platform) to force the payment status to paid before completing the order. This is useful for cash
         * payments, bank transfers, or other out-of-band payment methods.</p>
         *
         * @param params completion parameters including order ID and optional out-of-band payment flag
         * @return the completed order
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), payment not paid, or other API errors occur
         */
        public Order complete(OrderCompleteParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/complete", params);
        }

        /**
         * Cancels an existing order (POST /orders/cancel).
         *
         * <p>Transitions the order to cancelled status. Use this when an order should be voided or abandoned.
         * The order will reflect the {@code cancelled_at} timestamp. Cancelled orders cannot be paid or completed.</p>
         *
         * @param orderId unique identifier of the order to cancel
         * @return the cancelled order
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404) or cannot be cancelled
         */
        public Order cancel(String orderId) throws IOException, InterruptedException, ApiException {
            OrderCancelParams p = new OrderCancelParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("cancel", orderId);
            return cancel(p);
        }

        public Order cancel(OrderCancelParams params) throws IOException, InterruptedException, ApiException {
            return requestOrder("/orders/cancel", params);
        }

        /**
         * Compatibility alias for {@link RefundsClient#create(CreateRefundParams)}.
         * Accepts the same line-item refund request and returns the created refund.
         */
        @Deprecated
        public Refund refund(CreateRefundParams params) throws IOException, InterruptedException, ApiException {
            return requestResource("/orders/refund", params, "refund", Refund.class);
        }

        /** Compatibility alias with an explicit idempotency key. */
        @Deprecated
        public Refund refund(CreateRefundParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            JsonNode response = client.requestWithOptions("POST", "/orders/refund", params, options, JsonNode.class);
            return decodeResource(response, "refund", Refund.class);
        }

        /**
         * Retrieves a paginated list of orders (POST /orders/page).
         *
         * <p>Returns recent orders with pagination support. Use the {@code page_index} and {@code page_size}
         * parameters to control pagination. Orders are returned in reverse chronological order (newest first).</p>
         *
         * @param params pagination parameters including page index and size
         * @return the page of complete orders
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters are provided (400)
         */
        public OrderPage page(OrderPageParams params) throws IOException, InterruptedException, ApiException {
            return requestResource("/orders/page", params, "page", OrderPage.class);
        }

        private Order requestOrder(String path, Object params) throws IOException, InterruptedException, ApiException {
            return requestResource(path, params, "order", Order.class);
        }

        private <T> T requestResource(String path, Object params, String field, Class<T> resourceClass)
                throws IOException, InterruptedException, ApiException {
            JsonNode response = client.request("POST", path, params, JsonNode.class);
            return decodeResource(response, field, resourceClass);
        }

        private <T> T decodeResource(JsonNode response, String field, Class<T> resourceClass)
                throws JsonProcessingException {
            JsonNode resource = response.get(field);
            if (resource == null || resource.isNull()) {
                throw new IllegalStateException("Inttegro returned an invalid " + field + " response");
            }
            return client.mapper.treeToValue(resource, resourceClass);
        }

        private static RequestMeta stableOrderRequestMeta(String action, String orderId) {
            return RequestMeta.withIdempotencyKey("orders_" + action + "_" + orderId);
        }
    }

    public static class RefundsClient {
        private final Client client;

        public RefundsClient(Client client) {
            this.client = client;
        }

        /** Creates a full or partial refund against one or more paid order line items. */
        public Refund create(CreateRefundParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/refunds/create", params, "refund", Refund.class);
        }

        /** Creates a refund with an explicit idempotency key. */
        public Refund create(CreateRefundParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/refunds/create", params, options, "refund", Refund.class);
        }

        public Refund cancel(String refundId) throws IOException, InterruptedException, ApiException {
            return cancel(CancelRefundParams.builder().refundId(refundId).build());
        }

        public Refund cancel(CancelRefundParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/refunds/cancel", params, "refund", Refund.class);
        }

        public Refund cancel(CancelRefundParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/refunds/cancel", params, options, "refund", Refund.class);
        }

        public Refund lookup(String refundId) throws IOException, InterruptedException, ApiException {
            return lookup(LookupRefundParams.builder().refundId(refundId).build());
        }

        public Refund lookup(LookupRefundParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/refunds/lookup", params, "refund", Refund.class);
        }

        public RefundPage page(RefundPageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/refunds/page", params, "page", RefundPage.class);
        }
    }

    public static class ChimesClient {
        private final Client client;

        public ChimesClient(Client client) {
            this.client = client;
        }

        /**
         * Sends a notification message (chime) immediately to a single recipient (POST /chimes/send).
         *
         * <p>Delivers a chime via SMS or email using the specified or default transport mechanism. The chime
         * is sent immediately and returns delivery status information.</p>
         *
         * @param params chime parameters including recipient, message, and optional transport settings
         * @return {@link Chime} with chime ID and delivery status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public Chime send(SendChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/chimes/send", params, "chime", Chime.class);
        }

        /**
         * Retrieves the details of a previously sent chime by its ID (POST /chimes/lookup).
         *
         * <p>Returns full chime information including transmission status, delivery details, recipient,
         * message content, and timestamps.</p>
         *
         * @param chimeId unique identifier of the chime to lookup
         * @return {@link Chime} containing chime details and delivery status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if chime not found (404) or other API errors occur
         */
        public Chime lookup(String chimeId) throws IOException, InterruptedException, ApiException {
            LookupChimeParams p = new LookupChimeParams();
            p.chimeId = chimeId;
            return client.requestResource("/chimes/lookup", p, "chime", Chime.class);
        }

        /**
         * Schedules a notification message for delivery at a specific time (POST /chimes/schedule).
         *
         * <p>Can be sent to a single recipient or multiple recipients (broadcast). The chime will be
         * delivered on or after the specified time. Scheduled chimes can be cancelled before delivery.</p>
         *
         * @param params scheduling parameters including recipient(s), message, delivery time, and transport settings
         * @return {@link ScheduledChime} with scheduled chime details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public ScheduledChime schedule(ScheduleChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/chimes/schedule", params, "scheduled_chime", ScheduledChime.class);
        }

        /**
         * Broadcasts a chime to multiple recipients (POST /chimes/broadcast).
         *
         * <p>Queues a broadcast with a common message template and service context. Use broadcasts for
         * marketing announcements or bulk notifications.</p>
         *
         * @param params broadcast parameters including recipients and message template
         * @return {@link BroadcastDetail} summary of the queued broadcast
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public BroadcastDetail broadcast(BroadcastChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/chimes/broadcast", params, "broadcast", BroadcastDetail.class);
        }

        public ChimePage page(PageChimesParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/chimes/page", params, "page", ChimePage.class);
        }
    }

    public static class SchedulesClient {
        private final Client client;

        public SchedulesClient(Client client) {
            this.client = client;
        }

        /**
         * Retrieves scheduled chime details by schedule ID (POST /schedules/lookup).
         *
         * @param scheduleId schedule identifier
         * @return {@link ScheduleDetail} containing scheduled chime details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if schedule not found (404) or other API errors occur
         */
        public ScheduleDetail lookup(String scheduleId) throws IOException, InterruptedException, ApiException {
            LookupScheduleParams p = new LookupScheduleParams();
            p.scheduleId = scheduleId;
            return client.requestResource("/schedules/lookup", p, "scheduled_chime", ScheduleDetail.class);
        }

        /**
         * Cancels a scheduled chime by schedule ID (POST /schedules/cancel).
         *
         * @param scheduleId schedule identifier
         * @return {@link ScheduleDetail} containing the canceled schedule details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if schedule not found (404) or already executed/canceled (422)
         */
        public ScheduleDetail cancel(String scheduleId) throws IOException, InterruptedException, ApiException {
            CancelScheduleParams p = new CancelScheduleParams();
            p.scheduleId = scheduleId;
            return client.requestResource("/schedules/cancel", p, "scheduled_chime", ScheduleDetail.class);
        }
    }

    public static class BroadcastsClient {
        private final Client client;

        public BroadcastsClient(Client client) {
            this.client = client;
        }

        /**
         * Retrieves broadcast details by broadcast ID (POST /broadcasts/lookup).
         *
         * @param broadcastId broadcast identifier
         * @return {@link BroadcastDetail} containing broadcast details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if broadcast not found (404) or other API errors occur
         */
        public BroadcastDetail lookup(String broadcastId) throws IOException, InterruptedException, ApiException {
            LookupBroadcastParams p = new LookupBroadcastParams();
            p.broadcastId = broadcastId;
            return client.requestResource("/broadcasts/lookup", p, "broadcast", BroadcastDetail.class);
        }

        /**
         * Cancels a broadcast by broadcast ID (POST /broadcasts/cancel).
         *
         * @param broadcastId broadcast identifier
         * @return {@link BroadcastDetail} containing canceled broadcast details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if broadcast not found (404) or already completed/canceled (422)
         */
        public BroadcastDetail cancel(String broadcastId) throws IOException, InterruptedException, ApiException {
            CancelBroadcastParams p = new CancelBroadcastParams();
            p.broadcastId = broadcastId;
            return client.requestResource("/broadcasts/cancel", p, "broadcast", BroadcastDetail.class);
        }
    }

    public static class OtpClient {
        private final Client client;

        public OtpClient(Client client) {
            this.client = client;
        }

        /**
         * Initiates an OTP transaction and delivers the code to the recipient (POST /otp/initiate).
         *
         * <p>Creates a new OTP transaction and sends the verification code via SMS or email. Returns a
         * transaction identifier that must be used with the verify endpoint to validate the code.</p>
         *
         * @param payload initiation parameters including recipient contact and optional settings
         * @return map containing transaction information and delivery status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters or delivery fails
         */
        public OtpTransaction initiate(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/otp/initiate", payload, "transaction", OtpTransaction.class);
        }

        /**
         * Verifies a submitted OTP code for a given session (POST /otp/verify).
         *
         * <p>Validates the OTP code provided by the user against the session. Returns verification status
         * and any associated data. Failed verifications may allow retries up to a maximum attempt limit.</p>
         *
         * @param payload verification parameters including session identifier and OTP code
         * @return map containing verification result and status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if session invalid, code incorrect, or maximum attempts exceeded
         */
        public OtpVerification verify(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/otp/verify", payload, OtpVerification.class);
        }

        /**
         * Retrieves an existing OTP transaction (POST /otp/lookup).
         *
         * @param payload lookup parameters including transaction_id
         * @return map containing transaction information
         */
        public OtpTransaction lookup(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/otp/lookup", payload, "transaction", OtpTransaction.class);
        }

        /**
         * Cancels an OTP transaction (POST /otp/cancel).
         *
         * @param payload cancellation parameters including transaction_id and reason
         * @return map containing canceled transaction information
         */
        public OtpTransaction cancel(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/otp/cancel", payload, "transaction", OtpTransaction.class);
        }

        /**
         * Backwards-compatible alias for initiate().
         */
        public OtpTransaction initialize(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return initiate(payload);
        }
    }

    public static class PaymentMethodsClient {
        private final Client client;

        public PaymentMethodsClient(Client client) {
            this.client = client;
        }

        /**
         * Tokenizes and saves a payment method for future use (POST /payment_methods/tokenize).
         *
         * <p>Creates a reusable payment method token from payment details (mobile money account, card, etc.).
         * The tokenized method can be charged without requiring the customer to re-enter payment information.
         * Mobile money payment methods may require verification before they can be charged.</p>
         *
         * @param params tokenization parameters including customer ID and payment method details
         * @return {@link PaymentMethod} with the tokenized payment method
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid payment details or tokenization fails
         */
        public PaymentMethod tokenize(TokenizePaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/tokenize", params, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod tokenize(TokenizePaymentMethodParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/payment_methods/tokenize", params, options, "payment_method", PaymentMethod.class);
        }

        /**
         * Sends a verification challenge for a saved payment method (POST /payment_methods/verify).
         *
         * <p>Initiates verification by sending a code to the payment method (e.g., SMS to the mobile money number).
         * The customer must submit this code via the confirm verification endpoint to complete verification.
         * Verified payment methods can be charged without additional confirmation steps.</p>
         *
         * @param paymentMethodId unique identifier of the payment method to verify
         * @return {@link PaymentMethodVerificationSession} with verification session details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found or verification cannot be initiated
         */
        public PaymentMethodVerificationSession verify(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            VerifyPaymentMethodParams p = new VerifyPaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            p.requestMeta = stablePaymentMethodRequestMeta("verify", paymentMethodId);
            return verify(p);
        }

        public PaymentMethodVerificationSession verify(VerifyPaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/verify", params, "verification", PaymentMethodVerificationSession.class);
        }

        /**
         * Confirms a payment method verification with the token sent to the customer (POST /payment_methods/confirm_verification).
         *
         * <p>Completes the verification process by validating the code the customer received. Once verified,
         * the payment method's {@code verified} flag is set to true and it can be charged without additional
         * confirmation steps.</p>
         *
         * @param params confirmation parameters including payment method ID and verification token
         * @return {@link PaymentMethod} with the verified payment method
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if token invalid, session expired, or maximum attempts exceeded
         */
        public PaymentMethod confirmVerification(ConfirmPaymentMethodVerificationParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/confirm_verification", params, "payment_method", PaymentMethod.class);
        }

        /**
         * Retrieves details of a saved payment method by ID (POST /payment_methods/lookup).
         *
         * <p>Returns payment method information including type, masked details, verification status,
         * and associated customer. Sensitive information (full card numbers, account numbers) is not returned.</p>
         *
         * @param paymentMethodId unique identifier of the payment method
         * @return {@link PaymentMethod} containing payment method details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found (404)
         */
        public PaymentMethod lookup(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            LookupPaymentMethodParams p = new LookupPaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            return client.requestResource("/payment_methods/lookup", p, "payment_method", PaymentMethod.class);
        }

        public PaymentMethodPage page(PagePaymentMethodsParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/page", params, "page", PaymentMethodPage.class);
        }

        public PaymentMethod update(UpdatePaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/update", params, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod update(UpdatePaymentMethodParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/payment_methods/update", params, options, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod activate(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            return activate(PaymentMethodActionParams.builder().paymentMethodId(paymentMethodId).build());
        }

        public PaymentMethod activate(PaymentMethodActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/activate", params, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod disactivate(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            return disactivate(PaymentMethodActionParams.builder().paymentMethodId(paymentMethodId).build());
        }

        public PaymentMethod disactivate(PaymentMethodActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/disactivate", params, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod archive(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            return archive(PaymentMethodActionParams.builder().paymentMethodId(paymentMethodId).build());
        }

        public PaymentMethod archive(PaymentMethodActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/archive", params, "payment_method", PaymentMethod.class);
        }

        public PaymentMethod unarchive(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            return unarchive(PaymentMethodActionParams.builder().paymentMethodId(paymentMethodId).build());
        }

        public PaymentMethod unarchive(PaymentMethodActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/unarchive", params, "payment_method", PaymentMethod.class);
        }

        /**
         * Deletes a saved payment method (POST /payment_methods/delete).
         *
         * <p>Permanently removes the payment method from the customer's account. Deleted payment methods
         * cannot be recovered or used for future charges. Any in-progress payments using this method
         * will continue to completion.</p>
         *
         * @param paymentMethodId unique identifier of the payment method to delete
         * @return {@link PaymentMethodDeletion} confirming deletion
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found (404) or cannot be deleted
         */
        public PaymentMethodDeletion delete(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            DeletePaymentMethodParams p = new DeletePaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            p.requestMeta = stablePaymentMethodRequestMeta("delete", paymentMethodId);
            return delete(p);
        }

        public PaymentMethodDeletion delete(DeletePaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/delete", params, PaymentMethodDeletion.class);
        }

        /**
         * Retrieves payment method acceptance settings for your application (POST /payment_methods/settings).
         *
         * <p>Returns configuration for which payment method types are enabled, supported countries and currencies,
         * and any acceptance rules or restrictions.</p>
         *
         * @return {@link PaymentMethodSettings} with acceptance settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PaymentMethodSettings settings() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payment_methods/settings", new HashMap<>(), "settings", PaymentMethodSettings.class);
        }

        private static RequestMeta stablePaymentMethodRequestMeta(String action, String paymentMethodId) {
            return RequestMeta.withIdempotencyKey("payment_methods_" + action + "_" + paymentMethodId);
        }
    }

    public static class PayoutsClient {
        private final Client client;

        public PayoutsClient(Client client) {
            this.client = client;
        }

        /**
         * Configures payout destination financial accounts per currency (POST /payouts/set_destinations).
         *
         * <p>Maps each currency to a financial account ID where payouts in that currency should be sent.
         * For example, map "ghs" to a Ghana mobile money account and "usd" to a US bank account. Payouts
         * will automatically route to the appropriate destination based on the balance currency.</p>
         *
         * @param destinations map of currency codes to financial account IDs (e.g., {"ghs": "fa_123", "usd": "fa_456"})
         * @return {@link PayoutSettings} with updated payout settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid account IDs, unsupported currencies, or accounts not compatible with currencies
         */
        public PayoutSettings setDestinations(Map<String, String> destinations) throws IOException, InterruptedException, ApiException {
            Map<String, Object> body = new HashMap<>();
            body.put("destinations", destinations);
            return client.requestResource("/payouts/set_destinations", body, "settings", PayoutSettings.class);
        }

        /**
         * Retrieves current payout settings for your application (POST /payouts/settings).
         *
         * <p>Returns payout schedule configuration (automatic or manual), destination mappings per currency,
         * FX conversion settings, and any schedule-specific parameters.</p>
         *
         * @return {@link PayoutSettings} with payout configuration
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PayoutSettings settings() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/settings", new HashMap<>(), "settings", PayoutSettings.class);
        }

        /**
         * Disables automatic payouts and switches to manual mode (POST /payouts/disable).
         *
         * <p>Changes the payout schedule from automatic (e.g., weekly) to manual. In manual mode, payouts
         * only occur when explicitly triggered by you. Use this when you need full control over when funds
         * are transferred from your balance to your bank accounts.</p>
         *
         * @return {@link PayoutSettings} with updated settings showing manual schedule
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or already in manual mode
         */
        public PayoutSettings disableAutomatic() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/disable", new HashMap<>(), "settings", PayoutSettings.class);
        }

        public PayoutSettings enableAutomatic() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/enable", new HashMap<>(), "settings", PayoutSettings.class);
        }

        /**
         * Enables foreign exchange conversion for payouts (POST /payouts/enable_fx).
         *
         * <p>Allows payouts to be converted from the balance currency to the destination account currency.
         * For example, a GHS balance can be paid out to a USD bank account with automatic conversion.
         * Exchange rates are applied at the time of payout execution.</p>
         *
         * @return {@link PayoutSettings} with FX enabled in settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or FX not supported for your account
         */
        public PayoutSettings enableFX() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/enable_fx", new HashMap<>(), "settings", PayoutSettings.class);
        }

        /**
         * Disables foreign exchange conversion for payouts (POST /payouts/disable_fx).
         *
         * <p>Prevents currency conversion during payouts. When disabled, payouts can only be sent to
         * destination accounts that match the balance currency. Attempting to payout to a mismatched
         * currency account will fail.</p>
         *
         * @return {@link PayoutSettings} with FX disabled in settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PayoutSettings disableFX() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/disable_fx", new HashMap<>(), "settings", PayoutSettings.class);
        }

        /**
         * Retrieves a paginated list of payouts (POST /payouts/page).
         *
         * <p>Returns recent payouts with pagination support. Use {@code page_index} and {@code page_size}
         * to control pagination. Payouts are returned in reverse chronological order (newest first).
         * Each payout includes amount, destination, status, and timestamps.</p>
         *
         * @param params pagination parameters including page index and size
         * @return {@link PayoutPage} containing list of payouts and pagination details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters (400) or unauthorized (401)
         */
        public PayoutPage page(PayoutPageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/page", params, "page", PayoutPage.class);
        }

        public Payout lookup(String payoutId) throws IOException, InterruptedException, ApiException {
            return lookup(LookupPayoutParams.builder().payoutId(payoutId).build());
        }

        public Payout lookup(LookupPayoutParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/lookup", params, "payout", Payout.class);
        }

        public Payout schedule(SchedulePayoutParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/payouts/schedule", params, "payout", Payout.class);
        }

        /**
         * Cancels a scheduled payout before execution (POST /payouts/cancel).
         *
         * <p>Only payouts in {@code scheduled} status with future execution windows can be canceled.
         * Once canceled, the payout is permanently stopped.</p>
         *
         * @param payoutId scheduled payout identifier
         * @return {@link Payout} containing the canceled payout
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payout cannot be canceled or request is invalid
         */
        public Payout cancel(String payoutId) throws IOException, InterruptedException, ApiException {
            CancelPayoutParams p = new CancelPayoutParams();
            p.payoutId = payoutId;
            return client.requestResource("/payouts/cancel", p, "payout", Payout.class);
        }
    }

    public static class BalanceTransactionsClient {
        private final Client client;

        public BalanceTransactionsClient(Client client) {
            this.client = client;
        }

        /**
         * Retrieves a paginated list of balance transactions (POST /balance_transactions/page).
         *
         * <p>Returns transactions that affect your Inttegro balance, including charges, refunds, fees,
         * and payouts. Use {@code page_index} and {@code page_size} to control pagination. Transactions
         * are returned in reverse chronological order (newest first).</p>
         *
         * @param params pagination parameters including page index and size
         * @return {@link BalanceTransactionPage} containing transactions and pagination details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters (400) or unauthorized (401)
         */
        public BalanceTransactionPage page(BalanceTransactionPageParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/balance_transactions/page", params, "page", BalanceTransactionPage.class);
        }

        public BalanceTransaction lookup(String transactionId) throws IOException, InterruptedException, ApiException {
            return lookup(BalanceTransactionLookupParams.builder().transactionId(transactionId).build());
        }

        public BalanceTransaction lookup(BalanceTransactionLookupParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/balance_transactions/lookup", params, "transaction", BalanceTransaction.class);
        }
    }

    public static class BalancesClient {
        private final Client client;

        public BalancesClient(Client client) {
            this.client = client;
        }

        /**
         * Retrieves the current balances snapshot (POST /balances).
         *
         * @return {@link BalanceSnapshot} containing per-currency balance breakdowns
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public BalanceSnapshot get() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/balances", new HashMap<>(), "balances", BalanceSnapshot.class);
        }
    }

    public static class FinancialAccountsClient {
        private final Client client;

        public FinancialAccountsClient(Client client) {
            this.client = client;
        }

        /**
         * Creates a new financial account (POST /financial_accounts/create).
         *
         * <p>Registers a bank account or mobile money account for receiving payouts. The account details
         * must include sufficient information for funds transfer (account number, routing information, etc.).
         * Depending on the account type and country, verification may be required before the account can
         * receive payouts.</p>
         *
         * @param params account creation parameters including type, currency, and account details
         * @return {@link FinancialAccount} with the created account information
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid account details (400, 422) or unauthorized (401)
         */
        public FinancialAccount create(FinancialAccountCreateParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/create", params, "account", FinancialAccount.class);
        }

        /**
         * Retrieves details of a financial account by ID (POST /financial_accounts/lookup).
         *
         * <p>Returns account information including type, currency, masked account details, verification status,
         * and timestamps. Sensitive information (full account numbers) is not returned.</p>
         *
         * @param accountId unique identifier of the financial account
         * @return {@link FinancialAccount} containing account details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if account not found (404) or unauthorized (401)
         */
        public FinancialAccount lookup(String accountId) throws IOException, InterruptedException, ApiException {
            Map<String, String> body = new HashMap<>();
            body.put("account_id", accountId);
            return client.requestResource("/financial_accounts/lookup", body, "account", FinancialAccount.class);
        }

        public FinancialAccount reconnect(String accountId) throws IOException, InterruptedException, ApiException {
            FinancialAccountLookupParams params = FinancialAccountLookupParams.builder().accountId(accountId).build();
            return client.requestResource("/financial_accounts/reconnect", params, "account", FinancialAccount.class);
        }

        /**
         * Connects an existing financial account (POST /financial_accounts/connect).
         *
         * <p>Links an external account to your Inttegro application for payout destinations. The account
         * must already exist and belong to an entity authorized to receive funds on your behalf.</p>
         *
         * @param params connection parameters including account identifier and authorization details
         * @return {@link FinancialAccount} with connected account information
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if account cannot be connected, invalid authorization, or unauthorized (401)
         */
        public FinancialAccount connect(FinancialAccountCreateParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/connect", params, "account", FinancialAccount.class);
        }

        /**
         * Archives a financial account (POST /financial_accounts/archive).
         *
         * <p>Note: This endpoint currently returns HTTP 501 (Not Implemented) per the API specification.</p>
         *
         * @param payload archive parameters
         * @return map with response data (currently not implemented)
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or not implemented (501)
         */
        public FinancialAccount archive(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/archive", payload, "account", FinancialAccount.class);
        }

        /**
         * Retrieves a paginated list of financial accounts (POST /financial_accounts/page).
         *
         * @param params pagination parameters
         * @return {@link FinancialAccountsPage} with page metadata and accounts
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or validation failed (422)
         */
        public FinancialAccountsPage page(PageFinancialAccountsParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/page", params, "page", FinancialAccountsPage.class);
        }

        /**
         * Verifies a financial account (POST /financial_accounts/verify).
         *
         * <p>Note: This endpoint currently returns HTTP 501 (Not Implemented) per the API specification.</p>
         *
         * @param payload verification parameters
         * @return map with response data (currently not implemented)
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or not implemented (501)
         */
        public FinancialAccount verify(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/verify", payload, "account", FinancialAccount.class);
        }

        /**
         * Updates a financial account (POST /financial_accounts/update).
         *
         * <p>All fields except account_id are optional. custom_data merges with existing data.</p>
         *
         * @param params update parameters including account_id and fields to update
         * @return {@link FinancialAccount} containing updated account details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (422) or unauthorized (401)
         */
        public FinancialAccount update(FinancialAccountUpdateParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/update", params, "account", FinancialAccount.class);
        }

        /**
         * Enables push configuration for payouts (POST /financial_accounts/enable_push).
         */
        public FinancialAccount enablePush(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/enable_push", params, "account", FinancialAccount.class);
        }

        /**
         * Disables push configuration for payouts (POST /financial_accounts/disable_push).
         */
        public FinancialAccount disablePush(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/disable_push", params, "account", FinancialAccount.class);
        }

        /**
         * Enables pull configuration for charges (POST /financial_accounts/enable_pull).
         */
        public FinancialAccount enablePull(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/enable_pull", params, "account", FinancialAccount.class);
        }

        /**
         * Disables pull configuration for charges (POST /financial_accounts/disable_pull).
         */
        public FinancialAccount disablePull(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/disable_pull", params, "account", FinancialAccount.class);
        }

        public FinancialAccount disconnect(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/financial_accounts/disconnect", params, "account", FinancialAccount.class);
        }
    }

    public static class CustomersClient {
        private final Client client;

        public CustomersClient(Client client) {
            this.client = client;
        }

        public Customer create(CreateCustomerParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/customers/create", params, "customer", Customer.class);
        }

        public Customer lookup(String customerId) throws IOException, InterruptedException, ApiException {
            LookupCustomerParams p = new LookupCustomerParams();
            p.customerId = customerId;
            return client.requestResource("/customers/lookup", p, "customer", Customer.class);
        }

        public Customer update(UpdateCustomerParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/customers/update", params, "customer", Customer.class);
        }

        public CustomersPage page(PageCustomersParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/customers/page", params, "page", CustomersPage.class);
        }
    }

    public static class ProductsClient {
        private final Client client;

        public ProductsClient(Client client) {
            this.client = client;
        }

        public Product create(CreateProductParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/products/create", params, "product", Product.class);
        }

        public ProductDefaultUnitPrice addPrice(AddProductPriceParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/products/add_price", params, "price", ProductDefaultUnitPrice.class);
        }

        public Product setDefaultUnitPrice(SetDefaultUnitPriceParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/products/set_default_unit_price", params, "product", Product.class);
        }

        public Product lookup(String productId) throws IOException, InterruptedException, ApiException {
            LookupProductParams p = new LookupProductParams();
            p.productId = productId;
            return client.requestResource("/products/lookup", p, "product", Product.class);
        }

        public Product update(UpdateProductParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/products/update", params, "product", Product.class);
        }

        public Product publish(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.requestResource("/products/publish", p, "product", Product.class);
        }

        public Product unpublish(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.requestResource("/products/unpublish", p, "product", Product.class);
        }

        public Product archive(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.requestResource("/products/archive", p, "product", Product.class);
        }

        public ProductPage page(PageProductsParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/products/page", params, "page", ProductPage.class);
        }
    }

    public static class PricesClient {
        private final Client client;

        public PricesClient(Client client) {
            this.client = client;
        }

        public Price create(CreatePriceParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/create", params, "price", Price.class);
        }

        public Price lookup(String priceId) throws IOException, InterruptedException, ApiException {
            LookupPriceParams p = new LookupPriceParams();
            p.priceId = priceId;
            return client.requestResource("/prices/lookup", p, "price", Price.class);
        }

        public Price update(UpdatePriceParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/update", params, "price", Price.class);
        }

        public Price activate(String priceId) throws IOException, InterruptedException, ApiException {
            return activate(PriceActionParams.builder().priceId(priceId).build());
        }

        public Price activate(PriceActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/activate", params, "price", Price.class);
        }

        public Price deactivate(String priceId) throws IOException, InterruptedException, ApiException {
            return deactivate(PriceActionParams.builder().priceId(priceId).build());
        }

        public Price deactivate(PriceActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/deactivate", params, "price", Price.class);
        }

        public Price archive(String priceId) throws IOException, InterruptedException, ApiException {
            return archive(PriceActionParams.builder().priceId(priceId).build());
        }

        public Price archive(PriceActionParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/archive", params, "price", Price.class);
        }

        public PricePage page(PagePricesParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/prices/page", params, "page", PricePage.class);
        }
    }

    public static class SpecClient {
        private final Client client;

        public SpecClient(Client client) {
            this.client = client;
        }

        /**
         * Retrieves country specifications and payment capabilities (POST /spec/countries).
         *
         * <p>Returns supported countries with their available payment methods, currencies, and regulatory
         * requirements. This endpoint is public and does not require authentication. Use it to build dynamic
         * checkout forms that adapt to the customer's country.</p>
         *
         * @return {@link CountrySpecifications} with country specifications
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the request fails (rare, as this is a public endpoint)
         */
        public CountrySpecifications countries() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/spec/countries", new HashMap<>(), "countries", CountrySpecifications.class);
        }
    }

    public static class AppsClient {
        private final Client client;
        public AppsClient(Client client) { this.client = client; }

        /** Creates a Inttegro application. */
        public App create(CreateAppParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/apps/create", params, "app", App.class);
        }

        /** Retrieves the application associated with the configured API key. */
        public App lookup() throws IOException, InterruptedException, ApiException {
            return client.requestResource("/apps/lookup", Map.of(), "app", App.class);
        }

        /** Updates one or more attributes of the configured API key's application. */
        public App update(UpdateAppParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/apps/update", params, "app", App.class);
        }

        public App update(UpdateAppParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestResourceWithOptions("/apps/update", params, options, "app", App.class);
        }
    }

    public static class KeysClient {
        private final Client client;
        public KeysClient(Client client) { this.client = client; }

        public GeneratedSecretKey generate(GenerateSecretKeyParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/keys/generate", params, "key", GeneratedSecretKey.class);
        }

        public SecretKey lookup(String secretKeyId) throws IOException, InterruptedException, ApiException {
            return lookup(LookupSecretKeyParams.builder().secretKeyId(secretKeyId).build());
        }

        public SecretKey lookup(LookupSecretKeyParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/keys/lookup", params, "key", SecretKey.class);
        }

        public SecretKeyPage page(PageSecretKeysParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/keys/page", params, "page", SecretKeyPage.class);
        }

        public SecretKey update(UpdateSecretKeyParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/keys/update", params, "key", SecretKey.class);
        }

        public SecretKey destroy(String secretKeyId) throws IOException, InterruptedException, ApiException {
            return destroy(DestroySecretKeyParams.builder().secretKeyId(secretKeyId).build());
        }

        public SecretKey destroy(DestroySecretKeyParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/keys/destroy", params, "key", SecretKey.class);
        }

        public SecretKeyUsage usage(String secretKeyId) throws IOException, InterruptedException, ApiException {
            return usage(SecretKeyUsageParams.builder().secretKeyId(secretKeyId).build());
        }

        public SecretKeyUsage usage(SecretKeyUsageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/keys/usage", params, SecretKeyUsage.class);
        }
    }

    public static class PurchaseIntentsClient {
        private final Client client;
        public PurchaseIntentsClient(Client client) { this.client = client; }

        public PurchaseIntent create(CreatePurchaseIntentParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/purchase_intents/create", params, "purchase_intent", PurchaseIntent.class);
        }

        public PurchaseIntent lookup(String id) throws IOException, InterruptedException, ApiException {
            return lookup(LookupPurchaseIntentParams.builder().id(id).build());
        }

        public PurchaseIntent lookup(LookupPurchaseIntentParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/purchase_intents/lookup", params, "purchase_intent", PurchaseIntent.class);
        }

        public PurchaseIntentPage page(PagePurchaseIntentsParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/purchase_intents/page", params, "page", PurchaseIntentPage.class);
        }

        public PurchaseIntent update(UpdatePurchaseIntentParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/purchase_intents/update", params, "purchase_intent", PurchaseIntent.class);
        }

        public PurchaseIntent cancel(String id) throws IOException, InterruptedException, ApiException {
            return cancel(CancelPurchaseIntentParams.builder().id(id).build());
        }

        public PurchaseIntent cancel(CancelPurchaseIntentParams params) throws IOException, InterruptedException, ApiException {
            return client.requestResource("/purchase_intents/cancel", params, "purchase_intent", PurchaseIntent.class);
        }
    }
}
