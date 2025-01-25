package com.deva.ecommerce.orderline;

public record OrderLineRequest(Integer orderId, Integer productId, double quantity) {}
