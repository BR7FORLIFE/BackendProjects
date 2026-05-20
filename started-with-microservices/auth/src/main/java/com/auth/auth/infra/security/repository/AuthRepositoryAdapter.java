package com.auth.auth.infra.security.repository;

import org.springframework.stereotype.Repository;

import com.auth.auth.infra.security.repository.mongo.AuthRepository;

@Repository
public class AuthRepositoryAdapter {

    private final AuthRepository authRepository;

    public AuthRepositoryAdapter(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
}
