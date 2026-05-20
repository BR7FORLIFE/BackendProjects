package com.auth.auth.infra.security.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.auth.auth.infra.security.entity.AuthDocument;

public interface AuthRepository extends ReactiveMongoRepository<AuthDocument, UUID> {

}
