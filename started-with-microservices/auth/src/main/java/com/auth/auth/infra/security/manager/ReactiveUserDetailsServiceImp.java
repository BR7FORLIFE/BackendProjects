package com.auth.auth.infra.security.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth.auth.infra.security.ports.ReactiveUserDetailsServicePort;

import reactor.core.publisher.Mono;

@Component
public class ReactiveUserDetailsServiceImp implements ReactiveUserDetailsServicePort {
    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return null;
    }

    @Override
    public Mono<UserDetails> findByEmail(String email) {

        return null;
    }
}
