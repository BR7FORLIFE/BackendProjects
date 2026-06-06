package com.files.LiveProductLive.application.products.usecases;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface ProductUseCase {
    Mono<String> createProduct(String name, Float price);

    Mono<Void> establishViewByProductId(UUID id);
}
