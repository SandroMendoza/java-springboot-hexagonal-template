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

        this.id = UUID.randomUUID();
        this.productName = productName;
        adjustPrice(price);
        adjustStocks(stocks);
    }

    public static Product rehydrate(
        UUID id,
        String name,
        BigDecimal price,
        int stocks
    ) {
        Product product = new Product(name, price, stocks);
        product.id = id;
        return product;
    }

    public UUID getId() { return id; }
    public String getProductName() { return productName; }
    public BigDecimal getPrice() { return price; }
    public int getStocks() { return stocks; }
    public boolean isAvailable() { return isAvailable; }

    public void adjustPrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = newPrice;
    }

    public void adjustStocks(int newStocks) {
        if (newStocks < 0) {
            throw new IllegalArgumentException("Stocks cannot be negative");
        }
        this.stocks = newStocks;
        this.isAvailable = newStocks > 0;
    }
}
