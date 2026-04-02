package com.projects.adapters.persistence.mapper;

import com.projects.adapters.persistence.entity.ProductEntity;
import com.projects.core.domain.model.Product;

public class ProductEntityMapper implements EntityMapper<Product, ProductEntity> {

    @Override
    public ProductEntity toEntity(Product domain) {
        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getProductName());
        entity.setPrice(domain.getPrice());
        entity.setStocks(domain.getStocks());
        entity.setAvailable(domain.isAvailable());
        return entity;
    }

    @Override
    public Product toDomain(ProductEntity entity) {
        Product product = new Product(
            entity.getName(),
            entity.getPrice(),
            entity.getStocks()
        );
        product.setId(entity.getId());
        return product;
    }
}
