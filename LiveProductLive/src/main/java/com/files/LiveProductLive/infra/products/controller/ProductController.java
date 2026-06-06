package com.files.LiveProductLive.infra.products.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.files.LiveProductLive.application.products.dtos.CreateProductRequestDto;
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
        return productUseCase.createProduct(dto.name(), dto.price())
                .map(result -> ResponseEntity.ok().body(result));
    }

    @GetMapping
    public Mono<ResponseEntity<Void>> viewProduct(@RequestParam UUID id) {
        return productUseCase.establishViewByProductId(id)
                .thenReturn(ResponseEntity.ok().build());
    }
}
