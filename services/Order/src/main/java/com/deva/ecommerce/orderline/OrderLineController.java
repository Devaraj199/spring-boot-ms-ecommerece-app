package com.deva.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {
    @Autowired
    private  OrderLineService orderLineService;
    @GetMapping("/order/{order-line}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-line") Integer id) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(id));
    }
}
