package com.projects;

import com.projects.core.application.mapper.ProductReadModelMapper;
import com.projects.core.application.usecase.CreateProductUseCase;
import com.projects.core.application.port.out.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleBeanConfiguration {

    @Bean
    public CreateProductUseCase createProductUseCase(
        ProductRepository productRepository,
        ProductReadModelMapper mapper
    ) {
        return new CreateProductUseCase(productRepository, mapper);
    }

    @Bean
    public ProductReadModelMapper productReadModelMapper() {
        return new ProductReadModelMapper();
    }
}
