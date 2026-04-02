package com.projects.adapters.persistence.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D domain);
    D toDomain(E entity);
}
