package com.projects.core.application.usecase;

import com.projects.core.application.mapper.ProductReadModelMapper;
import com.projects.core.application.mapper.ReadModelMapper;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.command.CreateProductCommand;
import com.projects.core.application.port.out.ProductRepository;
import com.projects.core.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductUseCaseTest {

    @Test
    void shouldCreateProduct() {
        ProductRepository repository = mock(ProductRepository.class);
        ReadModelMapper<Product, ProductReadModel> mapper = new ProductReadModelMapper();

        CreateProductUseCase useCase = new CreateProductUseCase(repository, mapper);

        CreateProductCommand cmd = new CreateProductCommand(
            "Dimsum",
            BigDecimal.valueOf(10.20),
            100
        );

        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductReadModel readModel = useCase.execute(cmd);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(repository).save(productCaptor.capture());

        Product capturedProduct = productCaptor.getValue();

        assertEquals(cmd.name(), capturedProduct.getProductName());
        assertEquals(cmd.price(), capturedProduct.getPrice());
        assertEquals(cmd.stocks(), capturedProduct.getStocks());
        assertTrue(capturedProduct.isAvailable());

        assertEquals(cmd.name(), readModel.name());
        assertEquals(cmd.price(), readModel.price());
        assertEquals(cmd.stocks(), readModel.stocks());
        assertTrue(readModel.isAvailable());
    }
}
