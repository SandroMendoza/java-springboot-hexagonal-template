package com.projects.core.usecase;

import com.projects.core.application.mapper.ProductReadModelMapper;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.usecase.CreateProductUseCase;
import com.projects.core.domain.command.CreateProductCommand;
import com.projects.core.application.port.out.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {

    @Test
    void shouldCreateProduct() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductReadModelMapper mapper = new ProductReadModelMapper();

        CreateProductUseCase useCase = new CreateProductUseCase(repository, mapper);

        CreateProductCommand cmd = new CreateProductCommand(
                "Dimsum",
                BigDecimal.valueOf(10.20),
                100
        );

        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ProductReadModel readModel = useCase.execute(cmd);

        assertEquals(cmd.name(), readModel.name());
        assertEquals(cmd.price(), readModel.price());
        assertEquals(cmd.stocks(), readModel.stocks());
        assertTrue(readModel.isAvailable());

        verify(repository).save(any());
    }
}
