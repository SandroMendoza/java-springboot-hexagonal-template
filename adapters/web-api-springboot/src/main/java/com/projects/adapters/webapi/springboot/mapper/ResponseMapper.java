package com.projects.adapters.webapi.springboot.mapper;

public interface ResponseMapper<R, D> {
    D toDto(R readModel);
}
