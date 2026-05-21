package com.auth.auth.application.ports;

import com.auth.auth.domain.model.UserModel;

import reactor.core.publisher.Mono;

public interface IAuthRepositoryPort {
    Mono<UserModel> findByUsername(String username);

    Mono<UserModel> findByEmail(String email);

    Mono<UserModel> save(UserModel model);

    Mono<Boolean> existsUserByEmail(String email);
}
