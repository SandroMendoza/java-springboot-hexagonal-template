package com.projects.adapters.persistence.adapter;

import com.projects.adapters.persistence.entity.ProductEntity;
import com.projects.adapters.persistence.mapper.ProductEntityMapper;
import com.projects.adapters.persistence.repository.ProductJpaRepository;
import com.projects.core.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductPersistenceAdapterTest {

    @Test
    void shouldSaveProduct() {
        ProductJpaRepository productJpaRepository = mock(ProductJpaRepository.class);
        ProductEntityMapper productEntityMapper = new ProductEntityMapper();
        ProductPersistenceAdapter adapter = new ProductPersistenceAdapter(
            productJpaRepository,
            productEntityMapper
        );

        Product product = new Product(
            "Dimsum",
            BigDecimal.valueOf(10.20),
            100
        );

        when(productJpaRepository.save(any(ProductEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Product savedProduct = adapter.save(product);

        ArgumentCaptor<ProductEntity> entityCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productJpaRepository).save(entityCaptor.capture());

        ProductEntity capturedEntity = entityCaptor.getValue();

        assertNotNull(savedProduct);

        assertEquals(product.getId(), capturedEntity.getId());
        assertEquals(product.getProductName(), capturedEntity.getName());
        assertEquals(product.getPrice(), capturedEntity.getPrice());
        assertEquals(product.getStocks(), capturedEntity.getStocks());

        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStocks(), savedProduct.getStocks());
        assertEquals(product.isAvailable(), savedProduct.isAvailable());
    }
}
