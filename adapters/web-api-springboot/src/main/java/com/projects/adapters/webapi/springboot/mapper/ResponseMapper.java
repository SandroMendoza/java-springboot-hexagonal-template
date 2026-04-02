package com.projects.adapters.webapi.springboot.mapper;

public interface ResponseMapper<D, R> {
    D toDto(R readModel);
}
