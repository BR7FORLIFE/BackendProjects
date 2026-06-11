package com.files.LiveProductLive.infra.products.controller;

import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
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
import com.files.LiveProductLive.application.viewProducts.usecases.ProductViewUseCase;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductViewUseCase productViewUseCase;

    public ProductController(ProductUseCase productUseCase, ProductViewUseCase productViewUseCase) {
        this.productUseCase = productUseCase;
        this.productViewUseCase = productViewUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createProduct(@RequestBody CreateProductRequestDto dto) {
        return productUseCase.createProduct(dto.name(), dto.price(), dto.url())
                .map(result -> ResponseEntity.ok().body(result));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Void>> viewProduct(@PathVariable UUID id) {
        productViewUseCase.setViewInProduct(id);
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping
    public Mono<ResponseEntity<GetAllProductsResponseDto>> getAllProducts() {
        return productUseCase.getAll()
                .map(GetAllProductsResponseDto::new)
                .map(ResponseEntity::ok);
    }
}
