package com.projects.core.application.command;

import java.math.BigDecimal;

public record CreateProductCommand(
    String name,
    BigDecimal price,
    int stocks
) {}
