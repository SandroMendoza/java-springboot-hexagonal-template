package com.projects.adapters.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class ProductEntity {

    @Id
    private UUID id;

    private String name;
    private BigDecimal price;
    private int stocks;

    // Getters
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getStocks() { return stocks; }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setStocks(int stocks) {
        this.stocks = stocks;
    }
}
