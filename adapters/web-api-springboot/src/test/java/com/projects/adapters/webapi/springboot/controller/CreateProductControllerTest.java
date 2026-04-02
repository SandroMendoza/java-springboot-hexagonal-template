package com.projects.adapters.webapi.springboot.controller;

import com.projects.adapters.webapi.springboot.WebMvcTestBase;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.usecase.CreateProductUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateProductControllerTest extends WebMvcTestBase {

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @Test
    void shouldCreateProduct() throws Exception {
        UUID productId = UUID.randomUUID();

        ProductReadModel readModel = new ProductReadModel(
                productId,
                "Dimsum",
                BigDecimal.valueOf(10.20),
                100,
                true
        );

        when(createProductUseCase.execute(any())).thenReturn(readModel);

        String requestBody = """
                {
                  "name": "Dimsum",
                  "price": 10.20,
                  "stocks": 100
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.name").value("Dimsum"))
                .andExpect(jsonPath("$.price").value(10.20))
                .andExpect(jsonPath("$.stocks").value(100))
                .andExpect(jsonPath("$.isAvailable").value(true));

        verify(createProductUseCase).execute(any());
    }

    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
        String requestBody = """
                {
                  "name": "",
                  "price": 10.20,
                  "stocks": 100
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(createProductUseCase);
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsNull() throws Exception {
        String requestBody = """
                {
                  "name": "Dimsum",
                  "price": null,
                  "stocks": 100
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(createProductUseCase);
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsNotPositive() throws Exception {
        String requestBody = """
                {
                  "name": "Dimsum",
                  "price": 0,
                  "stocks": 100
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(createProductUseCase);
    }

    @Test
    void shouldReturnBadRequestWhenStocksIsNegative() throws Exception {
        String requestBody = """
                {
                  "name": "Dimsum",
                  "price": 10.20,
                  "stocks": -1
                }
                """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(createProductUseCase);
    }
}
