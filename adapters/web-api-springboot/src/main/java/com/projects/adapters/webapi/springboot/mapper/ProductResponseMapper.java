package com.projects.adapters.webapi.springboot.mapper;

import com.projects.adapters.webapi.springboot.response.CreateProductResponse;
import com.projects.core.application.readmodel.ProductReadModel;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements ResponseMapper<ProductReadModel, CreateProductResponse> {

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
