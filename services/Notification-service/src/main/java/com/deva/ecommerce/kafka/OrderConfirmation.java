package com.deva.ecommerce.kafka;

import com.deva.ecommerce.kafka.order.Customer;
import com.deva.ecommerce.kafka.order.Product;
import com.deva.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
