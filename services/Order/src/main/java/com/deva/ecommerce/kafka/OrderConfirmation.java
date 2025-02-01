package com.deva.ecommerce.kafka;

import com.deva.ecommerce.customer.CustomerResponse;
import com.deva.ecommerce.order.PaymentMethod;
import com.deva.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
