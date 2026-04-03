package com.projects;

import com.projects.core.application.mapper.ProductReadModelMapper;
import com.projects.core.application.mapper.ReadModelMapper;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.usecase.CreateProductUseCase;
import com.projects.core.application.port.out.ProductRepository;
import com.projects.core.domain.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleBeanConfiguration {

    @Bean
    public CreateProductUseCase createProductUseCase(
        ProductRepository productRepository,
        ReadModelMapper<Product, ProductReadModel> mapper
    ) {
        return new CreateProductUseCase(productRepository, mapper);
    }

    @Bean
    public ReadModelMapper<Product, ProductReadModel> productReadModelMapper() {
        return new ProductReadModelMapper();
    }
}
