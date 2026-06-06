package com.files.LiveProductLive.application.products.ports;

import java.util.UUID;

import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

public interface RedisRepositoryPort {
    Mono<Boolean> save(ProductModel model);

    Mono<Void> incrementViewsById(UUID id);
}
