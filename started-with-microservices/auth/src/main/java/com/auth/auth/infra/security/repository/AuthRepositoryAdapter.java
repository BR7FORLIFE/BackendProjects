package com.auth.auth.infra.security.repository;

import org.springframework.stereotype.Repository;

import com.auth.auth.application.ports.IAuthRepositoryPort;
import com.auth.auth.domain.model.UserModel;
import com.auth.auth.infra.security.entity.AuthDocument;
import com.auth.auth.infra.security.mapper.UserMapper;
import com.auth.auth.infra.security.repository.mongo.AuthRepository;

import reactor.core.publisher.Mono;

@Repository
public class AuthRepositoryAdapter implements IAuthRepositoryPort {

    private final AuthRepository authRepository;

    public AuthRepositoryAdapter(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Mono<UserModel> findByEmail(String email) {
        return authRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Mono<UserModel> findByUsername(String username) {
        return authRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsUserByEmail(String email) {
        return authRepository.existsByEmail(email);
    }

    @Override
    public Mono<UserModel> save(UserModel model) {
        AuthDocument document = UserMapper.toEntity(model);

        return authRepository.save(document)
                .map(UserMapper::toDomain);
    }
}
