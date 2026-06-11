package com.files.LiveProductLive.infra.redis.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import com.files.LiveProductLive.application.products.ports.RedisRepositoryPort;
import com.files.LiveProductLive.domain.products.ProductModel;

import reactor.core.publisher.Mono;

@Repository
public class RedisRepositoryAdapter implements RedisRepositoryPort {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public RedisRepositoryAdapter(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Boolean> save(ProductModel model) {
        Map<String, String> fields = Map.of(
                "id", model.getId().toString(),
                "name", model.getName(),
                "price", model.getPrice().toString(),
                "views", "0");

        return redisTemplate.opsForHash()
                .putAll(
                        "product:" + model.getId().toString(),
                        fields);
    }

    
    public Mono<Void> incrementViewsById(UUID id) {
        return redisTemplate.opsForHash().increment("product:" + id.toString(), "views", 1).then();
    }
}
