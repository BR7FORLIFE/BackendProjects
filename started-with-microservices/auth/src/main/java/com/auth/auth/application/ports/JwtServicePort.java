package com.auth.auth.application.ports;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface JwtServicePort {
    Mono<String> generateAccessToken(UserDetails userDetails);
}
