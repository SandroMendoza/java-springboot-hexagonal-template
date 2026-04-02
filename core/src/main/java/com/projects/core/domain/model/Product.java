package com.projects.core.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private UUID id;
    private final String productName;
    private BigDecimal price;
    private int stocks;
    private boolean isAvailable;

    public Product(String productName, BigDecimal price, int stocks) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stocks < 0) {
            throw new IllegalArgumentException("Stocks cannot be negative");
        }

        this.id = UUID.randomUUID();
        this.productName = productName;
        this.adjustPrice(price);
        adjustStocks(stocks);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getProductName() { return productName; }
    public BigDecimal getPrice() { return price; }
    public int getStocks() { return stocks; }
    public boolean isAvailable() { return isAvailable; }

    public void adjustPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public void adjustStocks(int newStocks) {
        this.stocks = newStocks;
        this.isAvailable = newStocks > 0;
    }
}
