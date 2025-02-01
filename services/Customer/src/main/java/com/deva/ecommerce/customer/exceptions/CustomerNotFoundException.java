package com.deva.ecommerce.customer.exceptions;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg) {
        super(msg); // Pass the message to the superclass
    }
}
