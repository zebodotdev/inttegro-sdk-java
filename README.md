# Inttegro Java SDK

[![OpenSSF Scorecard](https://api.scorecard.dev/projects/github.com/zebodotdev/inttegro-sdk-java/badge)](https://scorecard.dev/viewer/?uri=github.com/zebodotdev/inttegro-sdk-java)

The official Java client for building server-side Inttegro integrations.

> **Fastest, most modern path:** connect an agent to [Inttegro MCP](https://studio.inttegro.com/inttegro-mcp) at `https://mcp.inttegro.com`, then ask it to run `design_integration`. It will produce an implementation and test plan for your application. Use this SDK when you are ready to connect that plan to your Java service.

All official Inttegro SDKs expose the same API capabilities. This package adds Java-specific builders, domain types, and HTTP integration.

## Install

Requires Java 17 or newer.

```xml
<dependency>
  <groupId>com.inttegro</groupId>
  <artifactId>inttegro-sdk-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

Store your secret key in the server environment:

```bash
export INTTEGRO_API_KEY="your_secret_key"
```

Never put the key in browser code, a mobile app, or source control. The client uses `https://api.inttegro.com` by default.

## Create a hosted checkout

Create and finalize an order, then send the customer to its hosted invoice URL:

```java
import com.inttegro.ApiException;
import com.inttegro.Client;
import com.inttegro.ApiEnums;
import com.inttegro.RequestMeta;
import com.inttegro.common.Money;
import com.inttegro.customers.CustomerData;
import com.inttegro.orders.CheckoutSettings;
import com.inttegro.orders.Order;
import com.inttegro.orders.OrderCreateParams;
import com.inttegro.orders.OrderLineItem;

public class CheckoutExample {
  public static void main(String[] args) throws Exception {
    Client inttegro = new Client(System.getenv("INTTEGRO_API_KEY"));

    try {
      Order order = inttegro.orders().create(OrderCreateParams.builder()
          .requestMeta(RequestMeta.withIdempotencyKey("checkout-cart-123"))
          .customerData(CustomerData.builder()
              .name("Akua Mensah")
              .email("akua@example.com")
              .phoneNumber("+233544998605")
              .build())
          .finalizeOrder(true)
          .checkoutSettings(CheckoutSettings.builder()
              .redirectUrl("https://example.com/orders/complete")
              .cancelUrl("https://example.com/cart")
              .build())
          .lineItem(OrderLineItem.product(product -> product
              .type(ApiEnums.ProductType.DIGITAL)
              .name("Monthly subscription")
              .quantity(1)
              .price(Money.of("ghs", 5000))))
          .build());

      if (order.invoice == null || order.invoice.format == null || order.invoice.format.web == null) {
        throw new IllegalStateException("Order did not include a checkout URL");
      }
      System.out.println(order.id + " " + order.invoice.format.web.url);
    } catch (ApiException error) {
      System.err.println(error.getCode() + ": " + error.getDetail());
      throw error;
    }
  }
}
```

Amounts use integer minor units: `5000` GHS is GHS 50.00. Reuse the same idempotency key when retrying the same logical write. If you omit one, the SDK generates a UUIDv7 key for mutating calls.

## Create a partial refund

Refunds target paid order line items and return money to the original payment method:

```java
import com.inttegro.common.Money;
import com.inttegro.refunds.CreateRefundLineItem;
import com.inttegro.refunds.CreateRefundParams;
import com.inttegro.refunds.RefundReason;

var result = inttegro.refunds().create(CreateRefundParams.builder()
    .orderId("or_0123456789abcdefghijklmnopqrstuvwxyzABCD")
    .reason(RefundReason.ITEM_RETURNED)
    .lineItem(CreateRefundLineItem.builder()
        .orderLineItemId("oli_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN")
        .refundAmount(Money.of("ghs", 2500))
        .build())
    .build());

System.out.println(result.refund.id + " " + result.refund.status);
```

Use `refunds().cancel`, `refunds().lookup`, and `refunds().page` to manage the refund lifecycle. `orders().refund` remains a deprecated compatibility alias and returns the created `Refund` directly.

## Work with the API

The SDK covers orders and checkout, customers, products and prices, purchase intents, payment methods, balances, payouts and refunds, notifications, files, application settings, keys, and country specifications. Resource clients use camel-case fields such as `purchaseIntents` and `paymentMethods`.

Java-specific features:

- Typed request and domain types with fluent builders for common resources.
- Public constants for API enum values.
- JDK `HttpClient` transport with Jackson response mapping.
- An injectable `HttpClient` and base URL for connection pools, proxies, tests, and timeouts.
- A constructed client is safe to share across threads.
- Structured `ApiException` fields for status, code, detail, cause, and recovery guidance.

See the [API reference](https://studio.inttegro.com/api-reference) for request fields and lifecycle rules, [errors](https://studio.inttegro.com/errors) for recovery guidance, and [idempotency](https://studio.inttegro.com/idempotency) for safe retries.

## Verify a release

The GitHub release for each version is the canonical record. It contains the exact signed JAR, source JAR, Javadoc JAR, POM, and Maven Central publication bundle, plus SHA-256 checksums and a Sigstore attestation tied to the source commit and release workflow.

```bash
sha256sum --check SHA256SUMS
gh attestation verify inttegro-sdk-java-1.0.0.jar \
  --repo zebodotdev/inttegro-sdk-java
```

## Develop

```bash
mvn test
```
