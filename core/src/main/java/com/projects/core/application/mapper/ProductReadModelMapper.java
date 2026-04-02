package com.projects.core.application.mapper;

import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.domain.model.Product;

public class ProductReadModelMapper implements ReadModelMapper<ProductReadModel, Product> {

    @Override
    public ProductReadModel toReadModel(Product domain) {
        return new ProductReadModel(
            domain.getId(),
            domain.getProductName(),
            domain.getPrice(),
            domain.getStocks(),
            domain.isAvailable()
        );
    }
}
