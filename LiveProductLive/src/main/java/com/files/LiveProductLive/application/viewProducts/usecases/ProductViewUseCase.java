package com.files.LiveProductLive.application.viewProducts.usecases;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface ProductViewUseCase {
    Mono<Void> setViewInProduct(UUID productId);
}
