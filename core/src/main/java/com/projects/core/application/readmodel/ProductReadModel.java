package com.projects.core.application.readmodel;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductReadModel(
    UUID id,
    String name,
    BigDecimal price,
    int stocks,
    boolean isAvailable
) {
}
