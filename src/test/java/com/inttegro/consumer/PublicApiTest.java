package com.inttegro.consumer;

import com.inttegro.Client;
import com.inttegro.chimes.BroadcastDetail;
import com.inttegro.customers.BillingDetails;
import com.inttegro.orders.Order;
import com.inttegro.orders.OrderPage;
import com.inttegro.payments.Payment;
import com.inttegro.paymentmethods.MobileMoneyNetwork;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PublicApiTest {
    @Test
    void exposesClientAtTheRootAndTypesInDomainPackages() {
        Client client = new Client("sk_test_123");
        BillingDetails billingDetails = BillingDetails.builder()
                .name("Akua Mensah")
                .email("akua@example.com")
                .build();

        assertNotNull(client.orders());
        assertEquals("Akua Mensah", billingDetails.name);
        assertEquals("com.inttegro", Client.class.getPackageName());
        assertEquals("com.inttegro.orders", Order.class.getPackageName());
        assertEquals("com.inttegro.orders", OrderPage.class.getPackageName());
        assertEquals("com.inttegro.customers", BillingDetails.class.getPackageName());
        assertEquals("com.inttegro.payments", Payment.class.getPackageName());
        assertEquals("com.inttegro.chimes", BroadcastDetail.class.getPackageName());
        assertEquals("com.inttegro.paymentmethods", MobileMoneyNetwork.class.getPackageName());
    }
}
