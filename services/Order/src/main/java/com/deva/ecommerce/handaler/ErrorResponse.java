package com.deva.ecommerce.handaler;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors
) {
}
