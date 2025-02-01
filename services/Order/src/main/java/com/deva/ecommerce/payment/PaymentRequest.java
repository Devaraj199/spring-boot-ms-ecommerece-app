package com.deva.ecommerce.payment;

import com.deva.ecommerce.customer.CustomerResponse;
import com.deva.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
