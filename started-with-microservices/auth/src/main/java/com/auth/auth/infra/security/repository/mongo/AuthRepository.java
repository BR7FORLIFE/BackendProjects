package com.auth.auth.infra.security.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.auth.auth.infra.security.entity.AuthDocument;

import reactor.core.publisher.Mono;

public interface AuthRepository extends ReactiveMongoRepository<AuthDocument, UUID> {
    Mono<AuthDocument> findByEmail(String Email);

    Mono<AuthDocument> findByUsername(String Username);

    Mono<Boolean> existsByEmail(String Email);
}
