package com.deva.ecommerce.order;

import com.deva.ecommerce.customer.CustomerClient;
import com.deva.ecommerce.exception.BusinessException;
import com.deva.ecommerce.orderline.OrderLineRequest;
import com.deva.ecommerce.orderline.OrderLineService;
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
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper,
                        CustomerClient customerClient, ProductClient productClient,
                        OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
    }


    public Integer createOrder(OrderRequest orderRequest) {
//         check the customer -customer ms (openFeign)
            var customer  = this.customerClient.findCustomerById(orderRequest.customerId())
                    .orElseThrow(()->new BusinessException("Can't create order:: No Customer exist with the provided ID:" + orderRequest.customerId()));
// purchase product -> product microservice
          var x =  this.productClient.purchaseProducts(orderRequest.products());
        System.out.println(x);
//        // persist the order
//           var order =  this.orderRepository.save(orderMapper.toOrder(orderRequest));
        // persist the order lines
//        for(PurchaseRequest purchaseRequest:orderRequest.products()){
//            orderLineService.saveOrderLine(
//                    new OrderLineRequest(
//                            null,
//                            order.getId(),
//                            purchaseRequest.productId(),
//                            purchaseRequest.quantity()
//                    )
//            );
//        }
        // start payment process ->  payment ms
        // send the confirmation -> Notification MS(using kafka)

        return null;
    }


}
