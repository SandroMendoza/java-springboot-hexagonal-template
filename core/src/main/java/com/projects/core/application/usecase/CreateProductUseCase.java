package com.projects.core.application.usecase;

import com.projects.core.application.mapper.ProductReadModelMapper;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.domain.command.CreateProductCommand;
import com.projects.core.domain.model.Product;
import com.projects.core.application.port.out.ProductRepository;

public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final ProductReadModelMapper mapper;

    public CreateProductUseCase(
        ProductRepository productRepository,
        ProductReadModelMapper mapper
    ) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductReadModel execute(CreateProductCommand command) {
        Product product = new Product(
            command.name(),
            command.price(),
            command.stocks()
        );

        Product savedProduct = productRepository.save(product);
        return mapper.toReadModel(savedProduct);
    }
}
