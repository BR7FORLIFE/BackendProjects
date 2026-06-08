package com.files.LiveProductLive.infra.products.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.LiveProductLive.application.products.dtos.CreateProductRequestDto;
import com.files.LiveProductLive.application.products.dtos.GetAllProductsResponseDto;
import com.files.LiveProductLive.application.products.usecases.ProductUseCase;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createProduct(@RequestBody CreateProductRequestDto dto) {
        return productUseCase.createProduct(dto.name(), dto.price(), dto.url())
                .map(result -> ResponseEntity.ok().body(result));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Void>> viewProduct(@PathVariable UUID id) {
        return productUseCase.establishViewByProductId(id)
                .thenReturn(ResponseEntity.ok().build());
    }

    @GetMapping
    public Mono<ResponseEntity<GetAllProductsResponseDto>> getAllProducts() {
        return productUseCase.getAll()
                .map(GetAllProductsResponseDto::new)
                .map(ResponseEntity::ok);
    }
}
