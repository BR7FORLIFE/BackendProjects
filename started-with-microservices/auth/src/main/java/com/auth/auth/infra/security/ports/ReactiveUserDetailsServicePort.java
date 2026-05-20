package com.auth.auth.infra.security.ports;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface ReactiveUserDetailsServicePort extends ReactiveUserDetailsService {

    Mono<UserDetails> findByEmail(String email);
}
