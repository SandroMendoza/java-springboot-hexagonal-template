package com.projects.adapters.persistence.mapper;

import com.projects.adapters.persistence.entity.ProductEntity;
import com.projects.core.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper implements EntityMapper<Product, ProductEntity> {

    @Override
    public ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getProductName());
        entity.setPrice(product.getPrice());
        entity.setStocks(product.getStocks());
        return entity;
    }

    @Override
    public Product toDomain(ProductEntity entity) {
        return Product.rehydrate(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getStocks()
        );
    }
}
