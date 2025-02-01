package com.deva.ecommerce.customer;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService service;

    // Constructor-based injection
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createCustomer(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer( @Valid @RequestBody CustomerRequest request) {
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> isCustomerPresent(@PathVariable("customer-id") Long id) {
        return ResponseEntity.ok(this.service.isCustomerExist(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customer-id") Long id) {
        return ResponseEntity.ok(this.service.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.service.getAllCustomers());
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") Long id) {
        this.service.deleteCustomerById(id);
        return ResponseEntity.accepted().build();
    }
}
