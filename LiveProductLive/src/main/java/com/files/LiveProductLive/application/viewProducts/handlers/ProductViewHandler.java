package com.files.LiveProductLive.application.viewProducts.handlers;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface ProductViewHandler {
    Mono<Void> handle(UUID productId);
}
