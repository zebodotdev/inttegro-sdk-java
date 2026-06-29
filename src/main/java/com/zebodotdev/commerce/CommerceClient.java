package com.zebodotdev.commerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebodotdev.commerce.model.BalanceModels.BalanceTransactionPageParams;
import com.zebodotdev.commerce.model.BalanceModels.BalanceTransactionPageResponse;
import com.zebodotdev.commerce.model.ChimeModels.*;
import com.zebodotdev.commerce.model.CustomerModels.*;
import com.zebodotdev.commerce.model.OrderModels.*;
import com.zebodotdev.commerce.model.PaymentMethodModels.*;
import com.zebodotdev.commerce.model.PayoutModels.*;
import com.zebodotdev.commerce.model.FinancialModels.*;
import com.zebodotdev.commerce.model.ScheduleModels.*;
import com.zebodotdev.commerce.model.BroadcastModels.*;
import com.zebodotdev.commerce.model.SpecModels.CountriesResponse;
import com.zebodotdev.commerce.model.PlatformModels.*;
import com.zebodotdev.commerce.model.BalanceModels.*;
import com.zebodotdev.commerce.model.ProductModels.*;
import com.zebodotdev.commerce.model.PriceModels.*;
import com.zebodotdev.commerce.model.FileModels.*;
import com.zebodotdev.commerce.model.RequestMeta;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Main entry point for the Commerce HTTP API.
 *
     * <p>Usage (mirrors studio samples):
     * <pre>{@code
     * CommerceClient client = new CommerceClient(System.getenv("COMMERCE_API_KEY"));
     * var created = client.orders().create(
     *     OrderCreateParams.builder()
     *       .customerData(CustomerData.builder().name("Akua").phoneNumber("+233...").build())
     *       .paymentMethodData(PaymentMethodData.mobileMoney(m -> m.network("mtn").accountNumber("0544...")))
     *       .lineItem(OrderLineItem.product(p -> p.name("Subscription").type("digital").price(Money.of("ghs", 5000)).quantity(1)))
     *       .billingDetails(BillingDetails.builder().name("Akua").phoneNumber("+233...").build())
     *       .executePayment(true)
     *       .build()
     * ).order;
     * }</pre>
     *
     * Transport: JDK {@link java.net.http.HttpClient} (no external HTTP deps) with Jackson for JSON.<br/>
     * Auth: {@code Authorization: Bearer <key>} on every request.<br/>
     * Timeout: 30s default; supply your own HttpClient to change.<br/>
     * Thread safety: immutable after construction; share freely across goroutines/threads.</p>
 */
public class CommerceClient {
    private static final String DEFAULT_BASE_URL = "https://api.zebo.dev";
    private static final Set<String> NON_IDEMPOTENT_POST_ACTIONS = Set.of(
            "lookup", "page", "settings", "countries", "contents", "balances", "render_preview"
    );

    private final String apiKey;
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    private final SecureRandom random;

    public final OrdersClient orders;
    public final ChimesClient chimes;
    public final SchedulesClient schedules;
    public final BroadcastsClient broadcasts;
    public final OtpClient otp;
    public final PaymentMethodsClient paymentMethods;
    public final PayoutsClient payouts;
    public final BalanceTransactionsClient balanceTransactions;
    public final FinancialAccountsClient financialAccounts;
    public final FilesClient files;
    public final FileLinksClient fileLinks;
    public final UploadRequestsClient uploadRequests;
    public final MessageTemplatesClient messageTemplates;
    public final CustomersClient customers;
    public final ProductsClient products;
    public final PricesClient prices;
    public final SpecClient spec;
    public final PlatformClient platform;
    public final BalancesClient balances;

    /**
     * Create a client with the default production base URL.
     *
     * @param apiKey secret key from the dashboard (e.g. sk_test_... or sk_live_...)
     */
    public CommerceClient(String apiKey) {
        this(apiKey, DEFAULT_BASE_URL, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build());
    }

    /**
     * Create a client with a custom base URL and/or HttpClient.
     *
     * <p>Useful for pointing to a mock server in tests or a staging environment. Provide a custom
     * HttpClient if you need proxy support, custom timeouts, or a shared connection pool.</p>
     *
     * @param apiKey secret key
     * @param baseUrl override API host (e.g., https://staging-api.zebo.dev)
     * @param httpClient custom HttpClient (optional; pass null to use default)
     */
    public CommerceClient(String apiKey, String baseUrl, HttpClient httpClient) {
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
        this.chimes = new ChimesClient(this);
        this.schedules = new SchedulesClient(this);
        this.broadcasts = new BroadcastsClient(this);
        this.otp = new OtpClient(this);
        this.paymentMethods = new PaymentMethodsClient(this);
        this.payouts = new PayoutsClient(this);
        this.balanceTransactions = new BalanceTransactionsClient(this);
        this.financialAccounts = new FinancialAccountsClient(this);
        this.files = new FilesClient(this);
        this.fileLinks = new FileLinksClient(this);
        this.uploadRequests = new UploadRequestsClient(this);
        this.messageTemplates = new MessageTemplatesClient(this);
        this.customers = new CustomersClient(this);
        this.products = new ProductsClient(this);
        this.prices = new PricesClient(this);
        this.spec = new SpecClient(this);
        this.platform = new PlatformClient(this);
        this.balances = new BalancesClient(this);
    }

    // Convenience accessors matching doc samples.
    
    /**
     * Access the Orders resource for creating, paying, finalizing, and managing orders.
     * Orders represent complete customer transactions from cart to payment to fulfillment.
     *
     * @return orders client for order-related operations
     */
    public OrdersClient orders() { return orders; }

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
     * Payouts transfer funds from your Commerce balance to your bank accounts or mobile money.
     *
     * @return payouts client for payout operations
     */
    public PayoutsClient payouts() { return payouts; }
    
    /**
     * Access the Balance Transactions resource for retrieving transaction history.
     * Balance transactions show all changes to your Commerce balance including charges, refunds, and fees.
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
    public FileLinksClient fileLinks() { return fileLinks; }
    public UploadRequestsClient uploadRequests() { return uploadRequests; }
    public MessageTemplatesClient messageTemplates() { return messageTemplates; }
    public CustomersClient customers() { return customers; }
    public PricesClient prices() { return prices; }
    public ProductsClient products() { return products; }
    
    /**
     * Access the Spec resource for retrieving country specifications and payment capabilities.
     * Specifications define supported payment methods, currencies, and requirements per country.
     *
     * @return spec client for specification operations
     */
    public SpecClient spec() { return spec; }
    
    /**
     * Access the Platform resource for application, key, and session management.
     * Platform operations manage your Commerce applications and authentication credentials.
     *
     * @return platform client for platform-level operations
     */
    public PlatformClient platform() { return platform; }

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
                fields.put(entry.getKey().toString(), entry.getValue());
            }
        } else {
            fields = mapper.convertValue(params, Map.class);
        }
        pruneNulls(fields);
        return fields;
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

    private Map<String, Object> multipartRequest(String pathOrUrl, Map<String, Object> fields, Map<String, Path> files, RequestOptions options, boolean authenticated) throws IOException, InterruptedException, ApiException {
        String boundary = "----CommerceBoundary" + UUID.randomUUID();
        byte[] body = multipartBody(boundary, fields, files);
        String url = pathOrUrl.startsWith("http://") || pathOrUrl.startsWith("https://") ? pathOrUrl : baseUrl + pathOrUrl;
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Accept", "application/json")
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
        return mapper.readValue(response.body(), Map.class);
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
                .timeout(Duration.ofSeconds(30));
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
        private final CommerceClient client;

        public FilesClient(CommerceClient client) {
            this.client = client;
        }

        public Map<String, Object> create(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public Map<String, Object> create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            Object file = fields.remove("file");
            fields.remove("idempotency_key");
            return client.multipartRequest("/files/create", fields, Map.of("file", toPath(file)), options, true);
        }

        public Map<String, Object> create(FileCreateParams params) throws IOException, InterruptedException, ApiException {
            return create(params, null);
        }

        public Map<String, Object> create(FileCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            Object file = fields.remove("file");
            return client.multipartRequest("/files/create", fields, Map.of("file", toPath(file)), options, true);
        }

        public Map<String, Object> lookup(String fileId) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/files/lookup", Map.of("file_id", fileId), Map.class);
        }

        public Map<String, Object> page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/files/page", params, Map.class);
        }

        public Map<String, Object> page(FilePageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/files/page", client.toRequestMap(params), Map.class);
        }

        public FileDownload contents(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("POST", "/files/contents", params, true);
        }

        public FileDownload contents(FileContentsParams params) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("POST", "/files/contents", client.toRequestMap(params), true);
        }

        public Map<String, Object> delete(String fileId) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/files/delete", Map.of("file_id", fileId), Map.class);
        }
    }

    public static class FileLinksClient {
        private final CommerceClient client;

        public FileLinksClient(CommerceClient client) {
            this.client = client;
        }

        public Map<String, Object> create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/create", params, options, Map.class);
        }

        public Map<String, Object> create(FileLinkCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/create", client.toRequestMap(params), options, Map.class);
        }

        public Map<String, Object> lookup(String id) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/file_links/lookup", Map.of("id", id), Map.class);
        }

        public Map<String, Object> page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/file_links/page", params, Map.class);
        }

        public Map<String, Object> page(FileLinkPageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/file_links/page", client.toRequestMap(params), Map.class);
        }

        public Map<String, Object> revoke(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/revoke", params, options, Map.class);
        }

        public Map<String, Object> revoke(FileLinkRevokeParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/file_links/revoke", client.toRequestMap(params), options, Map.class);
        }

        public FileDownload open(String url) throws IOException, InterruptedException, ApiException {
            return client.binaryRequest("GET", url, null, false);
        }
    }

    public static class UploadRequestsClient {
        private final CommerceClient client;

        public UploadRequestsClient(CommerceClient client) {
            this.client = client;
        }

        public Map<String, Object> create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/upload_requests/create", params, options, Map.class);
        }

        public Map<String, Object> create(UploadRequestCreateParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/upload_requests/create", client.toRequestMap(params), options, Map.class);
        }

        public Map<String, Object> lookup(String id) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/upload_requests/lookup", Map.of("id", id), Map.class);
        }

        public Map<String, Object> page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/upload_requests/page", params, Map.class);
        }

        public Map<String, Object> page(UploadRequestPageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/upload_requests/page", client.toRequestMap(params), Map.class);
        }

        public Map<String, Object> cancel(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/upload_requests/cancel", params, options, Map.class);
        }

        public Map<String, Object> cancel(UploadRequestCancelParams params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/upload_requests/cancel", client.toRequestMap(params), options, Map.class);
        }

        public Map<String, Object> fulfill(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            String uploadURL = (String) params.get("upload_url");
            return client.multipartRequest(uploadURL, Map.of(), Map.of("file", toPath(params.get("file"))), null, false);
        }

        public Map<String, Object> fulfill(UploadRequestFulfillParams params) throws IOException, InterruptedException, ApiException {
            Map<String, Object> fields = client.toRequestMap(params);
            String uploadURL = (String) fields.get("upload_url");
            return client.multipartRequest(uploadURL, Map.of(), Map.of("file", toPath(fields.get("file"))), null, false);
        }
    }

    public static class MessageTemplatesClient {
        private final CommerceClient client;

        public MessageTemplatesClient(CommerceClient client) {
            this.client = client;
        }

        public Map<String, Object> create(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return create(params, generatedOptions());
        }

        public Map<String, Object> create(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/message_templates/create", params, optionsOrGenerated(options), Map.class);
        }

        public Map<String, Object> update(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return update(params, generatedOptions());
        }

        public Map<String, Object> update(Map<String, Object> params, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/message_templates/update", params, optionsOrGenerated(options), Map.class);
        }

        public Map<String, Object> publish(String templateId) throws IOException, InterruptedException, ApiException {
            return publish(templateId, generatedOptions());
        }

        public Map<String, Object> publish(String templateId, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/message_templates/publish", Map.of("id", templateId), optionsOrGenerated(options), Map.class);
        }

        public Map<String, Object> archive(String templateId) throws IOException, InterruptedException, ApiException {
            return archive(templateId, generatedOptions());
        }

        public Map<String, Object> archive(String templateId, RequestOptions options) throws IOException, InterruptedException, ApiException {
            return client.requestWithOptions("POST", "/message_templates/archive", Map.of("id", templateId), optionsOrGenerated(options), Map.class);
        }

        public Map<String, Object> lookup(String templateId) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/message_templates/lookup", Map.of("id", templateId), Map.class);
        }

        public Map<String, Object> page(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/message_templates/page", params, Map.class);
        }

        public Map<String, Object> renderPreview(Map<String, Object> params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/message_templates/render_preview", params, Map.class);
        }

        private static RequestOptions optionsOrGenerated(RequestOptions options) {
            return options == null || options.idempotencyKey() == null || options.idempotencyKey().isBlank()
                    ? generatedOptions()
                    : options;
        }

        private static RequestOptions generatedOptions() {
            return RequestOptions.withIdempotencyKey(UUID.randomUUID().toString());
        }
    }

    private static Path toPath(Object value) {
        if (value instanceof Path path) {
            return path;
        }
        return Path.of(value.toString());
    }

    public static class OrdersClient {
        private final CommerceClient client;

        public OrdersClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Creates a new order (POST /orders/new).
         *
         * <p>Creates a new order in the Commerce platform. Supports two flows:</p>
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
         * @return {@link CreateOrderResponse} with the created order plus optional {@code redirect_url} when action is required
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the API returns an error (400, 401, 422)
         */
        public CreateOrderResponse create(OrderCreateParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/new", params, CreateOrderResponse.class);
        }

        /**
         * Retrieves details of an existing order by its ID (POST /orders/lookup).
         *
         * <p>Returns full order details including line items, payment status, customer information,
         * invoice URLs, and all timestamps. The order may or may not exist.</p>
         *
         * @param orderId unique identifier of the order to lookup
         * @return {@link LookupOrderResponse} containing the order details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404) or other API errors occur
         */
        public LookupOrderResponse lookup(String orderId) throws IOException, InterruptedException, ApiException {
            OrderLookupParams p = new OrderLookupParams();
            p.orderId = orderId;
            return client.request("POST", "/orders/lookup", p, LookupOrderResponse.class);
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
         * @return {@link PaymentResponse} with payment status and optional next action (e.g., OTP confirmation)
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), already paid, or other API errors occur
         */
        public PaymentResponse pay(OrderPayParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/pay", params, PaymentResponse.class);
        }

        /**
         * Confirms a pending payment using a verification token (POST /orders/confirm_payment).
         *
         * <p>Use this endpoint when a payment requires confirmation (OTP verification). The payment response
         * will indicate {@code requires_confirmation=true} when a token has been sent to the customer's phone
         * or email. Submit the token the customer provides to complete the payment.</p>
         *
         * @param params confirmation parameters including order ID and verification token (OTP)
         * @return {@link LookupOrderResponse} containing the updated order with confirmed payment status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the token is invalid (400), order not found (404), or other API errors occur
         */
        public LookupOrderResponse confirmPayment(OrderConfirmParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/confirm_payment", params, LookupOrderResponse.class);
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
        public void requestConfirmation(String orderId) throws IOException, InterruptedException, ApiException {
            OrderRequestConfirmationParams p = new OrderRequestConfirmationParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("request_confirmation", orderId);
            requestConfirmation(p);
        }

        public void requestConfirmation(OrderRequestConfirmationParams params) throws IOException, InterruptedException, ApiException {
            client.request("POST", "/orders/request_confirmation", params, Void.class);
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
         * @return {@link LookupOrderResponse} containing the finalized order with invoice and checkout URLs
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), already finalized, or cannot be finalized (422)
         */
        public LookupOrderResponse finalizeOrder(String orderId) throws IOException, InterruptedException, ApiException {
            OrderFinalizeParams p = new OrderFinalizeParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("finalize", orderId);
            return finalizeOrder(p);
        }

        public LookupOrderResponse finalizeOrder(OrderFinalizeParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/finalize", params, LookupOrderResponse.class);
        }

        /**
         * Sends the hosted invoice link for an existing order (POST /orders/send_invoice).
         *
         * @param params send invoice parameters including order ID
         * @return {@link OrderDocumentDeliveryResponse} containing the order and delivery details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found or delivery fails
         */
        public OrderDocumentDeliveryResponse sendInvoice(OrderSendInvoiceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/send_invoice", params, OrderDocumentDeliveryResponse.class);
        }

        /**
         * Sends the hosted receipt link for a paid order (POST /orders/send_receipt).
         *
         * @param params send receipt parameters including order ID
         * @return {@link OrderDocumentDeliveryResponse} containing the order and delivery details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found, unpaid, or delivery fails
         */
        public OrderDocumentDeliveryResponse sendReceipt(OrderSendReceiptParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/send_receipt", params, OrderDocumentDeliveryResponse.class);
        }

        /**
         * Marks an order as completed (POST /orders/complete).
         *
         * <p>Transitions the order to the {@code completed} state. An order can only be completed if its
         * associated payment has been successfully paid. Once completed, the order status becomes {@code completed}
         * and the {@code completed_at} timestamp is set.</p>
         *
         * <p>Use the {@code paid_out_of_band} parameter when payment happened offline (outside the Commerce
         * platform) to force the payment status to paid before completing the order. This is useful for cash
         * payments, bank transfers, or other out-of-band payment methods.</p>
         *
         * @param params completion parameters including order ID and optional out-of-band payment flag
         * @return {@link LookupOrderResponse} containing the completed order
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), payment not paid, or other API errors occur
         */
        public LookupOrderResponse complete(OrderCompleteParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/complete", params, LookupOrderResponse.class);
        }

        /**
         * Cancels an existing order (POST /orders/cancel).
         *
         * <p>Transitions the order to cancelled status. Use this when an order should be voided or abandoned.
         * The order will reflect the {@code cancelled_at} timestamp. Cancelled orders cannot be paid or completed.</p>
         *
         * @param orderId unique identifier of the order to cancel
         * @return {@link LookupOrderResponse} containing the cancelled order
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404) or cannot be cancelled
         */
        public LookupOrderResponse cancel(String orderId) throws IOException, InterruptedException, ApiException {
            OrderCancelParams p = new OrderCancelParams();
            p.orderId = orderId;
            p.requestMeta = stableOrderRequestMeta("cancel", orderId);
            return cancel(p);
        }

        public LookupOrderResponse cancel(OrderCancelParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/cancel", params, LookupOrderResponse.class);
        }

        /**
         * Issues a refund for an order (POST /orders/refund).
         *
         * <p>Processes a full or partial refund for a completed order. The refunded amount is returned to
         * the customer's original payment method. The response returns the updated order with refund details.</p>
         *
         * @param orderId unique identifier of the order to refund
         * @return {@link LookupOrderResponse} containing the updated order with refund information
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the order is not found (404), not paid, or refund fails
         */
        public LookupOrderResponse refund(String orderId) throws IOException, InterruptedException, ApiException {
            OrderRefundParams p = new OrderRefundParams();
            p.orderId = orderId;
            return client.request("POST", "/orders/refund", p, LookupOrderResponse.class);
        }

        /**
         * Retrieves a paginated list of orders (POST /orders/page).
         *
         * <p>Returns recent orders with pagination support. Use the {@code page_index} and {@code page_size}
         * parameters to control pagination. Orders are returned in reverse chronological order (newest first).</p>
         *
         * @param params pagination parameters including page index and size
         * @return {@link PageOrdersResponse} containing the list of orders and pagination details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters are provided (400)
         */
        public PageOrdersResponse page(OrderPageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/orders/page", params, PageOrdersResponse.class);
        }

        private static RequestMeta stableOrderRequestMeta(String action, String orderId) {
            return RequestMeta.withIdempotencyKey("orders_" + action + "_" + orderId);
        }
    }

    public static class ChimesClient {
        private final CommerceClient client;

        public ChimesClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Sends a notification message (chime) immediately to a single recipient (POST /chimes/send).
         *
         * <p>Delivers a chime via SMS or email using the specified or default transport mechanism. The chime
         * is sent immediately and returns delivery status information.</p>
         *
         * @param params chime parameters including recipient, message, and optional transport settings
         * @return {@link ChimeResponse} with chime ID and delivery status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public ChimeResponse send(SendChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/chimes/send", params, ChimeResponse.class);
        }

        /**
         * Retrieves the details of a previously sent chime by its ID (POST /chimes/lookup).
         *
         * <p>Returns full chime information including transmission status, delivery details, recipient,
         * message content, and timestamps.</p>
         *
         * @param chimeId unique identifier of the chime to lookup
         * @return {@link ChimeResponse} containing chime details and delivery status
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if chime not found (404) or other API errors occur
         */
        public ChimeResponse lookup(String chimeId) throws IOException, InterruptedException, ApiException {
            LookupChimeParams p = new LookupChimeParams();
            p.chimeId = chimeId;
            return client.request("POST", "/chimes/lookup", p, ChimeResponse.class);
        }

        /**
         * Schedules a notification message for delivery at a specific time (POST /chimes/schedule).
         *
         * <p>Can be sent to a single recipient or multiple recipients (broadcast). The chime will be
         * delivered on or after the specified time. Scheduled chimes can be cancelled before delivery.</p>
         *
         * @param params scheduling parameters including recipient(s), message, delivery time, and transport settings
         * @return {@link ScheduleResponse} with scheduled chime details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public ScheduleResponse schedule(ScheduleChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/chimes/schedule", params, ScheduleResponse.class);
        }

        /**
         * Broadcasts a chime to multiple recipients (POST /chimes/broadcast).
         *
         * <p>Queues a broadcast with a common message template and service context. Use broadcasts for
         * marketing announcements or bulk notifications.</p>
         *
         * @param params broadcast parameters including recipients and message template
         * @return {@link BroadcastResponse} summary of the queued broadcast
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (400), unauthorized (401), or validation fails (422)
         */
        public BroadcastResponse broadcast(BroadcastChimeParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/chimes/broadcast", params, BroadcastResponse.class);
        }
    }

    public static class SchedulesClient {
        private final CommerceClient client;

        public SchedulesClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Retrieves scheduled chime details by schedule ID (POST /schedules/lookup).
         *
         * @param scheduleId schedule identifier
         * @return {@link ScheduleLookupResponse} containing scheduled chime details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if schedule not found (404) or other API errors occur
         */
        public ScheduleLookupResponse lookup(String scheduleId) throws IOException, InterruptedException, ApiException {
            LookupScheduleParams p = new LookupScheduleParams();
            p.scheduleId = scheduleId;
            return client.request("POST", "/schedules/lookup", p, ScheduleLookupResponse.class);
        }

        /**
         * Cancels a scheduled chime by schedule ID (POST /schedules/cancel).
         *
         * @param scheduleId schedule identifier
         * @return {@link ScheduleCancelResponse} containing the canceled schedule details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if schedule not found (404) or already executed/canceled (422)
         */
        public ScheduleCancelResponse cancel(String scheduleId) throws IOException, InterruptedException, ApiException {
            CancelScheduleParams p = new CancelScheduleParams();
            p.scheduleId = scheduleId;
            return client.request("POST", "/schedules/cancel", p, ScheduleCancelResponse.class);
        }
    }

    public static class BroadcastsClient {
        private final CommerceClient client;

        public BroadcastsClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Retrieves broadcast details by broadcast ID (POST /broadcasts/lookup).
         *
         * @param broadcastId broadcast identifier
         * @return {@link LookupBroadcastResponse} containing broadcast details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if broadcast not found (404) or other API errors occur
         */
        public LookupBroadcastResponse lookup(String broadcastId) throws IOException, InterruptedException, ApiException {
            LookupBroadcastParams p = new LookupBroadcastParams();
            p.broadcastId = broadcastId;
            return client.request("POST", "/broadcasts/lookup", p, LookupBroadcastResponse.class);
        }

        /**
         * Cancels a broadcast by broadcast ID (POST /broadcasts/cancel).
         *
         * @param broadcastId broadcast identifier
         * @return {@link BroadcastCancelResponse} containing canceled broadcast details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if broadcast not found (404) or already completed/canceled (422)
         */
        public BroadcastCancelResponse cancel(String broadcastId) throws IOException, InterruptedException, ApiException {
            CancelBroadcastParams p = new CancelBroadcastParams();
            p.broadcastId = broadcastId;
            return client.request("POST", "/broadcasts/cancel", p, BroadcastCancelResponse.class);
        }
    }

    public static class OtpClient {
        private final CommerceClient client;

        public OtpClient(CommerceClient client) {
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
        public Map<String, Object> initiate(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/otp/initiate", payload, Map.class);
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
        public Map<String, Object> verify(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/otp/verify", payload, Map.class);
        }

        /**
         * Retrieves an existing OTP transaction (POST /otp/lookup).
         *
         * @param payload lookup parameters including transaction_id
         * @return map containing transaction information
         */
        public Map<String, Object> lookup(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/otp/lookup", payload, Map.class);
        }

        /**
         * Cancels an OTP transaction (POST /otp/cancel).
         *
         * @param payload cancellation parameters including transaction_id and reason
         * @return map containing canceled transaction information
         */
        public Map<String, Object> cancel(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/otp/cancel", payload, Map.class);
        }

        /**
         * Backwards-compatible alias for initiate().
         */
        public Map<String, Object> initialize(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return initiate(payload);
        }
    }

    public static class PaymentMethodsClient {
        private final CommerceClient client;

        public PaymentMethodsClient(CommerceClient client) {
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
         * @return {@link PaymentMethodResponse} with the tokenized payment method
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid payment details or tokenization fails
         */
        public PaymentMethodResponse tokenize(TokenizePaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/tokenize", params, PaymentMethodResponse.class);
        }

        /**
         * Sends a verification challenge for a saved payment method (POST /payment_methods/verify).
         *
         * <p>Initiates verification by sending a code to the payment method (e.g., SMS to the mobile money number).
         * The customer must submit this code via the confirm verification endpoint to complete verification.
         * Verified payment methods can be charged without additional confirmation steps.</p>
         *
         * @param paymentMethodId unique identifier of the payment method to verify
         * @return {@link VerificationResponse} with verification session details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found or verification cannot be initiated
         */
        public VerificationResponse verify(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            VerifyPaymentMethodParams p = new VerifyPaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            p.requestMeta = stablePaymentMethodRequestMeta("verify", paymentMethodId);
            return verify(p);
        }

        public VerificationResponse verify(VerifyPaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/verify", params, VerificationResponse.class);
        }

        /**
         * Confirms a payment method verification with the token sent to the customer (POST /payment_methods/confirm_verification).
         *
         * <p>Completes the verification process by validating the code the customer received. Once verified,
         * the payment method's {@code verified} flag is set to true and it can be charged without additional
         * confirmation steps.</p>
         *
         * @param params confirmation parameters including payment method ID and verification token
         * @return {@link PaymentMethodResponse} with the verified payment method
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if token invalid, session expired, or maximum attempts exceeded
         */
        public PaymentMethodResponse confirmVerification(ConfirmPaymentMethodVerificationParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/confirm_verification", params, PaymentMethodResponse.class);
        }

        /**
         * Retrieves details of a saved payment method by ID (POST /payment_methods/lookup).
         *
         * <p>Returns payment method information including type, masked details, verification status,
         * and associated customer. Sensitive information (full card numbers, account numbers) is not returned.</p>
         *
         * @param paymentMethodId unique identifier of the payment method
         * @return {@link PaymentMethodResponse} containing payment method details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found (404)
         */
        public PaymentMethodResponse lookup(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            LookupPaymentMethodParams p = new LookupPaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            return client.request("POST", "/payment_methods/lookup", p, PaymentMethodResponse.class);
        }

        /**
         * Deletes a saved payment method (POST /payment_methods/delete).
         *
         * <p>Permanently removes the payment method from the customer's account. Deleted payment methods
         * cannot be recovered or used for future charges. Any in-progress payments using this method
         * will continue to completion.</p>
         *
         * @param paymentMethodId unique identifier of the payment method to delete
         * @return {@link DeletePaymentMethodResponse} confirming deletion
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payment method not found (404) or cannot be deleted
         */
        public DeletePaymentMethodResponse delete(String paymentMethodId) throws IOException, InterruptedException, ApiException {
            DeletePaymentMethodParams p = new DeletePaymentMethodParams();
            p.paymentMethodId = paymentMethodId;
            p.requestMeta = stablePaymentMethodRequestMeta("delete", paymentMethodId);
            return delete(p);
        }

        public DeletePaymentMethodResponse delete(DeletePaymentMethodParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/delete", params, DeletePaymentMethodResponse.class);
        }

        /**
         * Retrieves payment method acceptance settings for your application (POST /payment_methods/settings).
         *
         * <p>Returns configuration for which payment method types are enabled, supported countries and currencies,
         * and any acceptance rules or restrictions.</p>
         *
         * @return {@link PaymentMethodSettingsResponse} with acceptance settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PaymentMethodSettingsResponse settings() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payment_methods/settings", new HashMap<>(), PaymentMethodSettingsResponse.class);
        }

        private static RequestMeta stablePaymentMethodRequestMeta(String action, String paymentMethodId) {
            return RequestMeta.withIdempotencyKey("payment_methods_" + action + "_" + paymentMethodId);
        }
    }

    public static class PayoutsClient {
        private final CommerceClient client;

        public PayoutsClient(CommerceClient client) {
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
         * @return {@link PayoutSettingsResponse} with updated payout settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid account IDs, unsupported currencies, or accounts not compatible with currencies
         */
        public PayoutSettingsResponse setDestinations(Map<String, String> destinations) throws IOException, InterruptedException, ApiException {
            Map<String, Object> body = new HashMap<>();
            body.put("destinations", destinations);
            return client.request("POST", "/payouts/set_destinations", body, PayoutSettingsResponse.class);
        }

        /**
         * Retrieves current payout settings for your application (POST /payouts/settings).
         *
         * <p>Returns payout schedule configuration (automatic or manual), destination mappings per currency,
         * FX conversion settings, and any schedule-specific parameters.</p>
         *
         * @return {@link PayoutSettingsResponse} with payout configuration
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PayoutSettingsResponse settings() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payouts/settings", new HashMap<>(), PayoutSettingsResponse.class);
        }

        /**
         * Disables automatic payouts and switches to manual mode (POST /payouts/disable).
         *
         * <p>Changes the payout schedule from automatic (e.g., weekly) to manual. In manual mode, payouts
         * only occur when explicitly triggered by you. Use this when you need full control over when funds
         * are transferred from your balance to your bank accounts.</p>
         *
         * @return {@link PayoutSettingsResponse} with updated settings showing manual schedule
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or already in manual mode
         */
        public PayoutSettingsResponse disableAutomatic() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payouts/disable", new HashMap<>(), PayoutSettingsResponse.class);
        }

        /**
         * Enables foreign exchange conversion for payouts (POST /payouts/enable_fx).
         *
         * <p>Allows payouts to be converted from the balance currency to the destination account currency.
         * For example, a GHS balance can be paid out to a USD bank account with automatic conversion.
         * Exchange rates are applied at the time of payout execution.</p>
         *
         * @return {@link PayoutSettingsResponse} with FX enabled in settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or FX not supported for your account
         */
        public PayoutSettingsResponse enableFX() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payouts/enable_fx", new HashMap<>(), PayoutSettingsResponse.class);
        }

        /**
         * Disables foreign exchange conversion for payouts (POST /payouts/disable_fx).
         *
         * <p>Prevents currency conversion during payouts. When disabled, payouts can only be sent to
         * destination accounts that match the balance currency. Attempting to payout to a mismatched
         * currency account will fail.</p>
         *
         * @return {@link PayoutSettingsResponse} with FX disabled in settings
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public PayoutSettingsResponse disableFX() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payouts/disable_fx", new HashMap<>(), PayoutSettingsResponse.class);
        }

        /**
         * Retrieves a paginated list of payouts (POST /payouts/page).
         *
         * <p>Returns recent payouts with pagination support. Use {@code page_index} and {@code page_size}
         * to control pagination. Payouts are returned in reverse chronological order (newest first).
         * Each payout includes amount, destination, status, and timestamps.</p>
         *
         * @param params pagination parameters including page index and size
         * @return {@link PayoutPageResponse} containing list of payouts and pagination details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters (400) or unauthorized (401)
         */
        public PayoutPageResponse page(PayoutPageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/payouts/page", params, PayoutPageResponse.class);
        }

        /**
         * Cancels a scheduled payout before execution (POST /payouts/cancel).
         *
         * <p>Only payouts in {@code scheduled} status with future execution windows can be canceled.
         * Once canceled, the payout is permanently stopped.</p>
         *
         * @param payoutId scheduled payout identifier
         * @return {@link CancelPayoutResponse} containing the canceled payout
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if payout cannot be canceled or request is invalid
         */
        public CancelPayoutResponse cancel(String payoutId) throws IOException, InterruptedException, ApiException {
            CancelPayoutParams p = new CancelPayoutParams();
            p.payoutId = payoutId;
            return client.request("POST", "/payouts/cancel", p, CancelPayoutResponse.class);
        }
    }

    public static class BalanceTransactionsClient {
        private final CommerceClient client;

        public BalanceTransactionsClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Retrieves a paginated list of balance transactions (POST /balance_transactions/page).
         *
         * <p>Returns transactions that affect your Commerce balance, including charges, refunds, fees,
         * and payouts. Use {@code page_index} and {@code page_size} to control pagination. Transactions
         * are returned in reverse chronological order (newest first).</p>
         *
         * @param params pagination parameters including page index and size
         * @return {@link BalanceTransactionPageResponse} containing transactions and pagination details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid pagination parameters (400) or unauthorized (401)
         */
        public BalanceTransactionPageResponse page(BalanceTransactionPageParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/balance_transactions/page", params, BalanceTransactionPageResponse.class);
        }
    }

    public static class BalancesClient {
        private final CommerceClient client;

        public BalancesClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Retrieves the current balances snapshot (POST /balances).
         *
         * @return {@link BalancesResponse} containing per-currency balance breakdowns
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401)
         */
        public BalancesResponse get() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/balances", new HashMap<>(), BalancesResponse.class);
        }
    }

    public static class FinancialAccountsClient {
        private final CommerceClient client;

        public FinancialAccountsClient(CommerceClient client) {
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
         * @return {@link FinancialAccountResponse} with the created account information
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid account details (400, 422) or unauthorized (401)
         */
        public FinancialAccountResponse create(FinancialAccountCreateParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/create", params, FinancialAccountResponse.class);
        }

        /**
         * Retrieves details of a financial account by ID (POST /financial_accounts/lookup).
         *
         * <p>Returns account information including type, currency, masked account details, verification status,
         * and timestamps. Sensitive information (full account numbers) is not returned.</p>
         *
         * @param accountId unique identifier of the financial account
         * @return {@link FinancialAccountResponse} containing account details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if account not found (404) or unauthorized (401)
         */
        public FinancialAccountResponse lookup(String accountId) throws IOException, InterruptedException, ApiException {
            Map<String, String> body = new HashMap<>();
            body.put("account_id", accountId);
            return client.request("POST", "/financial_accounts/lookup", body, FinancialAccountResponse.class);
        }

        /**
         * Connects an existing financial account (POST /financial_accounts/connect).
         *
         * <p>Links an external account to your Commerce application for payout destinations. The account
         * must already exist and belong to an entity authorized to receive funds on your behalf.</p>
         *
         * @param params connection parameters including account identifier and authorization details
         * @return {@link FinancialAccountResponse} with connected account information
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if account cannot be connected, invalid authorization, or unauthorized (401)
         */
        public FinancialAccountResponse connect(FinancialAccountCreateParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/connect", params, FinancialAccountResponse.class);
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
        public Map<String, Object> archive(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/archive", payload, Map.class);
        }

        /**
         * Retrieves a paginated list of financial accounts (POST /financial_accounts/page).
         *
         * @param params pagination parameters
         * @return {@link FinancialAccountsPageResponse} with page metadata and accounts
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if unauthorized (401) or validation failed (422)
         */
        public FinancialAccountsPageResponse page(PageFinancialAccountsParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/page", params, FinancialAccountsPageResponse.class);
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
        public Map<String, Object> verify(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/verify", payload, Map.class);
        }

        /**
         * Updates a financial account (POST /financial_accounts/update).
         *
         * <p>All fields except account_id are optional. custom_data merges with existing data.</p>
         *
         * @param params update parameters including account_id and fields to update
         * @return {@link FinancialAccountResponse} containing updated account details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if invalid parameters (422) or unauthorized (401)
         */
        public FinancialAccountResponse update(FinancialAccountUpdateParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/update", params, FinancialAccountResponse.class);
        }

        /**
         * Enables push configuration for payouts (POST /financial_accounts/enable_push).
         */
        public Map<String, Object> enablePush(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/enable_push", params, Map.class);
        }

        /**
         * Disables push configuration for payouts (POST /financial_accounts/disable_push).
         */
        public Map<String, Object> disablePush(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/disable_push", params, Map.class);
        }

        /**
         * Enables pull configuration for charges (POST /financial_accounts/enable_pull).
         */
        public Map<String, Object> enablePull(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/enable_pull", params, Map.class);
        }

        /**
         * Disables pull configuration for charges (POST /financial_accounts/disable_pull).
         */
        public Map<String, Object> disablePull(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/disable_pull", params, Map.class);
        }

        public FinancialAccountResponse disconnect(FinancialAccountToggleParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/financial_accounts/disconnect", params, FinancialAccountResponse.class);
        }
    }

    public static class CustomersClient {
        private final CommerceClient client;

        public CustomersClient(CommerceClient client) {
            this.client = client;
        }

        public CustomerResponse create(CreateCustomerParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/customers/create", params, CustomerResponse.class);
        }

        public CustomerResponse lookup(String customerId) throws IOException, InterruptedException, ApiException {
            LookupCustomerParams p = new LookupCustomerParams();
            p.customerId = customerId;
            return client.request("POST", "/customers/lookup", p, CustomerResponse.class);
        }

        public CustomersPageResponse page(PageCustomersParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/customers/page", params, CustomersPageResponse.class);
        }
    }

    public static class ProductsClient {
        private final CommerceClient client;

        public ProductsClient(CommerceClient client) {
            this.client = client;
        }

        public ProductResponse create(CreateProductParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/products/create", params, ProductResponse.class);
        }

        public AddProductPriceResponse addPrice(AddProductPriceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/products/add_price", params, AddProductPriceResponse.class);
        }

        public ProductResponse setDefaultUnitPrice(SetDefaultUnitPriceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/products/set_default_unit_price", params, ProductResponse.class);
        }

        public ProductResponse lookup(String productId) throws IOException, InterruptedException, ApiException {
            LookupProductParams p = new LookupProductParams();
            p.productId = productId;
            return client.request("POST", "/products/lookup", p, ProductResponse.class);
        }

        public ProductResponse update(UpdateProductParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/products/update", params, ProductResponse.class);
        }

        public ProductResponse publish(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.request("POST", "/products/publish", p, ProductResponse.class);
        }

        public ProductResponse unpublish(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.request("POST", "/products/unpublish", p, ProductResponse.class);
        }

        public ProductResponse archive(String productId) throws IOException, InterruptedException, ApiException {
            ProductActionParams p = new ProductActionParams();
            p.productId = productId;
            return client.request("POST", "/products/archive", p, ProductResponse.class);
        }

        public PageProductsResponse page(PageProductsParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/products/page", params, PageProductsResponse.class);
        }
    }

    public static class PricesClient {
        private final CommerceClient client;

        public PricesClient(CommerceClient client) {
            this.client = client;
        }

        public PriceResponse create(CreatePriceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/prices/create", params, PriceResponse.class);
        }

        public PriceResponse lookup(String priceId) throws IOException, InterruptedException, ApiException {
            LookupPriceParams p = new LookupPriceParams();
            p.priceId = priceId;
            return client.request("POST", "/prices/lookup", p, PriceResponse.class);
        }

        public PriceResponse update(UpdatePriceParams params) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/prices/update", params, PriceResponse.class);
        }
    }

    public static class SpecClient {
        private final CommerceClient client;

        public SpecClient(CommerceClient client) {
            this.client = client;
        }

        /**
         * Retrieves country specifications and payment capabilities (POST /spec/countries).
         *
         * <p>Returns supported countries with their available payment methods, currencies, and regulatory
         * requirements. This endpoint is public and does not require authentication. Use it to build dynamic
         * checkout forms that adapt to the customer's country.</p>
         *
         * @return {@link CountriesResponse} with country specifications
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if the request fails (rare, as this is a public endpoint)
         */
        public CountriesResponse countries() throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/spec/countries", new HashMap<>(), CountriesResponse.class);
        }
    }

    public static class PlatformClient {
        private final CommerceClient client;
        public PlatformClient(CommerceClient client) { this.client = client; }

        /**
         * Creates a new Commerce application (POST /apps/create).
         *
         * <p>Platform-level endpoint for creating application instances. Request and response shapes
         * are defined by platform requirements.</p>
         *
         * @param payload application creation parameters
         * @return {@link CreateAppResponse} with created application details
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if creation fails or unauthorized
         */
        public CreateAppResponse createApp(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/apps/create", payload, CreateAppResponse.class);
        }

        /**
         * Generates a new API key for an application (POST /keys/generate).
         *
         * <p>Platform-level endpoint for API key management. Returns the generated key which should
         * be stored securely. Keys cannot be retrieved again after generation.</p>
         *
         * @param payload key generation parameters including scope and permissions
         * @return {@link GenerateKeyResponse} with the generated API key
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if generation fails or unauthorized
         */
        public GenerateKeyResponse generateKey(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/keys/generate", payload, GenerateKeyResponse.class);
        }

        /**
         * Creates a new session (POST /sessions/new).
         *
         * <p>Platform-level endpoint for session management. Used for dashboard and embedded integration
         * authentication flows.</p>
         *
         * @param payload session creation parameters
         * @return {@link NewSessionResponse} with session details and tokens
         * @throws IOException if network communication fails
         * @throws InterruptedException if the request is interrupted
         * @throws ApiException if creation fails or unauthorized
         */
        public NewSessionResponse newSession(Map<String, Object> payload) throws IOException, InterruptedException, ApiException {
            return client.request("POST", "/sessions/new", payload, NewSessionResponse.class);
        }
    }
}
