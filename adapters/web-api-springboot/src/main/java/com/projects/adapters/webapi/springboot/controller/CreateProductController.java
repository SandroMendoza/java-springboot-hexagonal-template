package com.projects.adapters.webapi.springboot.controller;

import com.projects.adapters.webapi.springboot.ApiBase;
import com.projects.adapters.webapi.springboot.mapper.ProductResponseMapper;
import com.projects.adapters.webapi.springboot.request.CreateProductRequest;
import com.projects.adapters.webapi.springboot.response.CreateProductResponse;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.usecase.CreateProductUseCase;
import com.projects.core.application.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CreateProductController implements ApiBase {

    private final CreateProductUseCase createProductUseCase;
    private final ProductResponseMapper mapper;

    public CreateProductController(
            CreateProductUseCase createProductUseCase,
            ProductResponseMapper mapper
    ) {
        this.createProductUseCase = createProductUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/products")
    public ResponseEntity<CreateProductResponse> createProduct(
        @Valid @RequestBody final CreateProductRequest request
    ) {
        CreateProductCommand command = new CreateProductCommand(
            request.name(),
            request.price(),
            request.stocks()
        );

        ProductReadModel createdProduct = createProductUseCase.execute(command);
        CreateProductResponse response = mapper.toDto(createdProduct);

        return ResponseEntity
            .created(URI.create("/products" + response.id()))
            .body(response);
    }
}
