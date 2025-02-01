package com.deva.ecommerce.order;

import com.deva.ecommerce.customer.CustomerClient;
import com.deva.ecommerce.exception.BusinessException;
import com.deva.ecommerce.kafka.OrderConfirmation;
import com.deva.ecommerce.kafka.OrderProducer;
import com.deva.ecommerce.orderline.OrderLineRequest;
import com.deva.ecommerce.orderline.OrderLineService;
import com.deva.ecommerce.payment.PaymentClient;
import com.deva.ecommerce.payment.PaymentRequest;
import com.deva.ecommerce.product.ProductClient;
import com.deva.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper,
                        CustomerClient customerClient, ProductClient productClient,
                        OrderLineService orderLineService,PaymentClient paymentClient,OrderProducer orderProducer) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
        this.paymentClient  = paymentClient;
        this.orderProducer = orderProducer;
    }


    public Integer createOrder(OrderRequest orderRequest) {
//         check the customer -customer ms (openFeign)
            var customer  = this.customerClient.findCustomerById(orderRequest.customerId())
                    .orElseThrow(()->new BusinessException("Can't create order:: No Customer exist with the provided ID:" + orderRequest.customerId()));
// purchase product -> product microservice
        System.out.println(customer);
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());
//         persist the order
        var order =  this.orderRepository.save(orderMapper.toOrder(orderRequest));
        // persist the order lines
        System.out.println(order);
        for(PurchaseRequest purchaseRequest:orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        // start payment process ->  payment ms
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);
        // send the confirmation -> Notification MS(using kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }


}
