package com.inttegro;

import com.inttegro.orders.Order;
import com.inttegro.orders.OrderStatus;
import com.inttegro.paymentmethods.PaymentMethod;
import com.inttegro.payments.Payment;
import com.inttegro.payments.PaymentNextAction;
import com.inttegro.payments.PaymentNextActionType;
import com.inttegro.payments.PaymentStatus;
import com.inttegro.products.Product;
import com.inttegro.purchaseintents.PurchaseIntent;
import com.inttegro.purchaseintents.PurchaseIntentStatus;
import com.inttegro.purchaseintents.PurchaseIntentUsage;
import com.inttegro.purchaseintents.PurchaseIntentUsageOrder;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResourceSemanticsTest {
    @Test
    void answersPaymentAndOrderQuestions() {
        PaymentNextAction action = new PaymentNextAction();
        action.type = PaymentNextActionType.REDIRECT;
        Payment payment = new Payment();
        payment.status = PaymentStatus.REQUIRES_ACTION;
        payment.nextAction = action;
        Order order = new Order();
        order.status = OrderStatus.REQUIRES_PAYMENT;
        order.payment = payment;

        assertTrue(payment.requiresAction());
        assertFalse(payment.isTerminal());
        assertSame(action, payment.requiredAction());
        assertTrue(order.requiresPayment());
        assertSame(action, order.requiredPaymentAction());
    }

    @Test
    void answersCatalogAndPaymentMethodQuestions() {
        PurchaseIntentUsageOrder usedOrder = new PurchaseIntentUsageOrder();
        usedOrder.id = "or_123";
        PurchaseIntentUsage usage = PurchaseIntentUsage.builder().singleUse(true).order(usedOrder).build();
        PurchaseIntent intent = new PurchaseIntent();
        intent.status = PurchaseIntentStatus.USED;
        intent.usage = usage;

        Product product = new Product();
        product.active = true;
        product.publishedAt = OffsetDateTime.now();
        PaymentMethod method = new PaymentMethod();
        method.active = true;
        method.verifiedAt = OffsetDateTime.now();

        assertTrue(intent.isSingleUse());
        assertEquals("or_123", intent.usedOrderId());
        assertTrue(product.isPublished());
        assertTrue(product.wasEverPublished());
        assertTrue(method.isVerified());
        assertTrue(method.isReusable());
    }
}
