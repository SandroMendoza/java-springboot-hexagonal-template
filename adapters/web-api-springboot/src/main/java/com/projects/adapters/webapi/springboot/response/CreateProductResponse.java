package com.projects.adapters.webapi.springboot.response;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductResponse(
    UUID id,
    String name,
    BigDecimal price,
    int stocks,
    boolean isAvailable
) {
}
