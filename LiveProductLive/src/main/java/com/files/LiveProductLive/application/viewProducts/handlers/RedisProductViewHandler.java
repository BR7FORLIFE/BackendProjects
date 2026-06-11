package com.files.LiveProductLive.application.viewProducts.handlers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.files.LiveProductLive.application.products.ports.RedisRepositoryPort;

import reactor.core.publisher.Mono;

@Component
public class RedisProductViewHandler implements ProductViewHandler {

    private final RedisRepositoryPort redisRepositoryPort;

    public RedisProductViewHandler(RedisRepositoryPort redisRepositoryPort) {
        this.redisRepositoryPort = redisRepositoryPort;
    }

    @Override
    public Mono<Void> handle(UUID productId) {
        return redisRepositoryPort.incrementViewsById(productId);
    }
}
