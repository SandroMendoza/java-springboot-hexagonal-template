package com.projects.adapters.webapi.springboot.mapper;

import com.projects.adapters.webapi.springboot.response.CreateProductResponse;
import com.projects.core.application.readmodel.ProductReadModel;

public class ProductResponseMapper implements ResponseMapper<CreateProductResponse, ProductReadModel> {

    @Override
    public CreateProductResponse toDto(ProductReadModel readModel) {
        return new CreateProductResponse(
            readModel.id(),
            readModel.name(),
            readModel.price(),
            readModel.stocks(),
            readModel.isAvailable()
        );
    }
}
