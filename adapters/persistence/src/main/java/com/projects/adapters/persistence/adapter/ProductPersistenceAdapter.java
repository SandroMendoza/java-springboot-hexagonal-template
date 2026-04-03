package com.projects.adapters.persistence.adapter;

import com.projects.adapters.persistence.entity.ProductEntity;
import com.projects.adapters.persistence.mapper.ProductEntityMapper;
import com.projects.adapters.persistence.repository.ProductJpaRepository;
import com.projects.core.domain.model.Product;
import com.projects.core.application.port.out.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductEntityMapper mapper;

    public ProductPersistenceAdapter(
            ProductJpaRepository productJpaRepository,
            ProductEntityMapper mapper
    ) {
        this.productJpaRepository = productJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity savedEntity = productJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
