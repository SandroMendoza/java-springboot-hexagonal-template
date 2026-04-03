package com.projects.adapters.webapi.springboot.controller;

import com.projects.adapters.webapi.springboot.WebMvcTestBase;
import com.projects.core.application.readmodel.ProductReadModel;
import com.projects.core.application.usecase.CreateProductUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

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
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productId.toString()))
            .andExpect(jsonPath("$.name").value("Dimsum"))
            .andExpect(jsonPath("$.price").value(10.20))
            .andExpect(jsonPath("$.stocks").value(100))
            .andExpect(jsonPath("$.isAvailable").value(true))
            .andExpect(header().string("Location", "/api/products/" + productId));

        verify(createProductUseCase).execute(any());
    }

    @ParameterizedTest
    @MethodSource("invalidCreateProductRequests")
    void shouldReturnBadRequestForInvalidRequest(
        String requestBody,
        String expectedMessage,
        String expectedDetail
    ) throws Exception {
        var resultActions = mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value(expectedMessage));

        if (expectedDetail != null) {
            resultActions.andExpect(jsonPath("$.details[0]").value(expectedDetail));
        }

        verifyNoInteractions(createProductUseCase);
    }

    private static Stream<Arguments> invalidCreateProductRequests() {
        return Stream.of(
            Arguments.of(
                """
                    {
                      "name": "",
                      "price": 10.20,
                      "stocks": 100
                    }
                """,
                "Validation failed",
                "name: must not be blank"
            ),
            Arguments.of(
                """
                    {
                      "name": "Dimsum",
                      "price": null,
                      "stocks": 100
                    }
                """,
                "Validation failed",
                "price: must not be null"
            ),
            Arguments.of(
                """
                    {
                      "name": "Dimsum",
                      "price": 0,
                      "stocks": 100
                    }
                """,
                "Validation failed",
                "price: must be greater than 0"
            ),
            Arguments.of(
                """
                    {
                      "name": "Dimsum",
                      "price": 10.20,
                      "stocks": -1
                    }
                """,
                "Validation failed",
                "stocks: must be greater than or equal to 0"
            )
        );
    }
}
