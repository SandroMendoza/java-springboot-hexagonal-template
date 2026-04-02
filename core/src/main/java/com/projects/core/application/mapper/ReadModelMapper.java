package com.projects.core.application.mapper;

public interface ReadModelMapper<R, D> {
    R toReadModel(D domain);
}
