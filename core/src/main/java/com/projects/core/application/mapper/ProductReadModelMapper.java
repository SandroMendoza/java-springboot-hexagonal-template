package com.projects.core.application.mapper;

import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.domain.model.Product;

public class ProductReadModelMapper implements ReadModelMapper<Product, ProductReadModel> {

    @Override
    public ProductReadModel toReadModel(Product product) {
        return new ProductReadModel(
            product.getId(),
            product.getProductName(),
            product.getPrice(),
            product.getStocks(),
            product.isAvailable()
        );
    }
}
