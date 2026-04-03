package com.projects.core.application.mapper;

public interface ReadModelMapper<D, R> {
    R toReadModel(D domain);
}
