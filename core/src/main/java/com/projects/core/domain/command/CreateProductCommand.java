package com.projects.core.domain.command;

import java.math.BigDecimal;

public record CreateProductCommand(
    String name,
    BigDecimal price,
    int stocks
) {}
