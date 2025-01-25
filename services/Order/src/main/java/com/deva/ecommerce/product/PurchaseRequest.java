package com.deva.ecommerce.product;

import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchaseRequest(
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
