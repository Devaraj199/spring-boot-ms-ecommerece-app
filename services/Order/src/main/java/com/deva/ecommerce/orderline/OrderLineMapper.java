package com.deva.ecommerce.orderline;

import com.deva.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        if (orderLineRequest == null) {
            return null;
        }

        return OrderLine.builder()
                .productId(orderLineRequest.productId())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .quantity(orderLineRequest.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
