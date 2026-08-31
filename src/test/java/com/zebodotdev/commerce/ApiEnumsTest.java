package com.zebodotdev.commerce;

import com.zebodotdev.commerce.model.ApiEnums;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiEnumsTest {
    @Test
    void exposesExactWireValues() {
        assertEquals("digital", ApiEnums.ProductType.DIGITAL);
        assertEquals("requested_by_customer", ApiEnums.RefundReason.REQUESTED_BY_CUSTOMER);
        assertEquals("pending", ApiEnums.UploadRequestStatus.PENDING);
    }
}
