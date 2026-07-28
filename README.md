# Commerce Java SDK

Minimal Java client for the Commerce API using only the JDK `HttpClient` and Jackson for JSON.

## Installation

Add to your Maven project:

```xml
<dependency>
  <groupId>com.zebodotdev</groupId>
  <artifactId>commerce-sdk-java</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

## Quickstart

```java
import com.zebodotdev.commerce.CommerceClient;
import com.zebodotdev.commerce.model.CommonModels.*;
import com.zebodotdev.commerce.model.CustomerModels.*;
import com.zebodotdev.commerce.model.AppsModels.*;
import com.zebodotdev.commerce.model.OrderModels.*;
import com.zebodotdev.commerce.model.PaymentMethodModels.*;

public class Example {
  public static void main(String[] args) throws Exception {
    CommerceClient client = new CommerceClient(System.getenv("COMMERCE_API_KEY"));

    OrderPayoutSettings payoutSettings = new OrderPayoutSettings();
    OrderPayoutDestination destination = new OrderPayoutDestination();
    destination.financialAccountId = "fa_1234567890abcdef";
    payoutSettings.destination = destination;
    payoutSettings.enableFX = false;

    OrderCreateParams params = OrderCreateParams.builder()
        .customerData(CustomerData.builder()
            .name("Akua Mensah")
            .phoneNumber("+233544998605")
            .build())
        .paymentMethodData(PaymentMethodData.mobileMoney(momo -> {
          momo.issuer("mtn").number("0544998605");
        }))
        .lineItem(OrderLineItem.product(prod -> prod
            .name("Monthly Subscription")
            .type("digital")
            .price(Money.of("ghs", 5000))
            .quantity(1)))
        .billingDetails(BillingDetails.builder()
            .name("Akua Mensah")
            .phoneNumber("+233544998605")
            .email("akua@example.com")
            .address(Address.builder()
                .name("Akua Mensah")
                .phoneNumber("+233544998605")
                .line1("23 Adenta High Street")
                .town("Accra")
                .country("GH")
                .build())
            .build())
        .payoutSettings(payoutSettings)
        .executePayment(true)
        .build();

    var created = client.orders().create(params);
    System.out.println("Order created: " + created.order.id);
  }
}
```

## Resource snippets

```java
// Lookup an order
var order = client.orders.lookup("or_123").order;

// Pay with saved method
OrderPayParams pay = new OrderPayParams();
pay.orderId = "or_123";
var payResp = client.orders.pay(pay);

// Payment methods
TokenizePaymentMethodParams tok = new TokenizePaymentMethodParams();
tok.customerId = "cu_123";
tok.paymentMethodData = pm;
var saved = client.paymentMethods.tokenize(tok).paymentMethod;

// Chimes
SendChimeParams ch = new SendChimeParams();
ch.fullMessage = "Your code is 123456";
ch.recipient = new ChimeRecipient();
ch.recipient.type = ChimeRecipientType.PHONE;
Phone phone = new Phone();
phone.number = "+233544998605";
ch.recipient.phone = phone;
var chime = client.chimes.send(ch).chime;

// Schedule a chime
ScheduleChimeParams sched = new ScheduleChimeParams();
sched.recipients = List.of("+233544998605", "user@example.com");
sched.fullMessage = "Reminder: payment due tomorrow";
sched.sendAfter = "2026-01-18T10:00:00Z";
sched.senderId = "YourBrand";
var scheduled = client.chimes.schedule(sched).scheduledChime;

// Broadcast a chime
BroadcastChimeParams bcast = new BroadcastChimeParams();
bcast.recipients = List.of("+233544998605", "user@example.com");
bcast.messageTemplate = "Hello! Check out our new product launch.";
bcast.serviceName = "MarketingCampaign";
bcast.sender = "YourBrand";
var broadcast = client.chimes.broadcast(bcast);

// Lookup/cancel schedules and broadcasts
var scheduleInfo = client.schedules.lookup("sch_abc123def456ghi789").scheduledChime;
var canceledSchedule = client.schedules.cancel("sch_abc123def456ghi789").scheduledChime;
var broadcastInfo = client.broadcasts.lookup("brc_abc123def456ghi789").broadcast;
var canceledBroadcast = client.broadcasts.cancel("brc_abc123def456ghi789").broadcast;

// Payout settings
var settings = client.payouts.setDestinations(Map.of("ghs", "fa_123")).settings;
var canceledPayout = client.payouts.cancel("po_123").payout;

// Countries
var countries = client.spec.countries().countries;

// Customers
CreateCustomerParams cust = new CreateCustomerParams();
cust.name = "Jane Doe";
cust.emailAddress = "jane@example.com";
cust.phoneNumber = "+233501234567";
var customer = client.customers.create(cust).customer;

var existing = client.customers.lookup("cu_123").customer;
var customerPage = client.customers.page(new PageCustomersParams()).page;

// Products
CreateProductParams prod = new CreateProductParams();
prod.type = "physical";
prod.name = "Premium Cotton T-Shirt";
var createdProduct = client.products.create(prod).product;

AddProductPriceParams productPrice = new AddProductPriceParams();
productPrice.productId = createdProduct.id;
productPrice.amount = new ProductPriceAmount();
productPrice.amount.currency = "ghs";
productPrice.amount.value = 5000L;
productPrice.setAsDefault = true;
client.products.addPrice(productPrice);

var productPage = client.products.page(new PageProductsParams()).page;
var published = client.products.publish(createdProduct.id).product;

// Prices
CreatePriceParams price = new CreatePriceParams();
price.currency = "USD";
price.amount = 1999L;
price.label = "Standard pricing";
var createdPrice = client.prices.create(price).price;
UpdatePriceParams updatePrice = new UpdatePriceParams();
updatePrice.priceId = createdPrice.id;
updatePrice.label = "Premium pricing";
var updatedPrice = client.prices.update(updatePrice).price;

// Apps
var createdApp = client.apps().create(
    CreateAppParams.builder().name("My App").build()
).app;
var currentApp = client.apps().lookup().app;
var updatedApp = client.apps().update(
    UpdateAppParams.builder().alias("my-app").build()
).app;
```

## Errors

API errors throw `ApiException` with status code and error code/message:

```java
try {
  client.orders.lookup("bad");
} catch (ApiException e) {
  System.err.println(e.getStatusCode() + " " + e.getMessage());
}
```

## Testing

```bash
cd sdks/java
mvn test
```
