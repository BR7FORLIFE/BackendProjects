package com.auth.auth.infra.security.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth.auth.infra.security.ports.ReactiveUserDetailsServicePort;
import com.auth.auth.infra.security.repository.AuthRepositoryAdapter;

import reactor.core.publisher.Mono;

//este servico se va a usar si hay una session statefull donde hay que consultar la base de datos haber si existe
//pero como es stateless gracias a los jwt la session se hara en el authentication manager
@Component
public class ReactiveUserDetailsServiceImp implements ReactiveUserDetailsServicePort {

    private final AuthRepositoryAdapter authRepositoryAdapter;

    public ReactiveUserDetailsServiceImp(AuthRepositoryAdapter authRepositoryAdapter) {
        this.authRepositoryAdapter = authRepositoryAdapter;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return authRepositoryAdapter.findByUsername(username)
                .flatMap(user -> {
                    List<GrantedAuthority> rols = user.getRols()
                            .stream()
                            .map(String::toString)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    return Mono.just(new UserDetailsImp(user, user.getPassword(), rols));
                });
    }

    @Override
    public Mono<UserDetails> findByEmail(String email) {
        return authRepositoryAdapter.findByEmail(email)
                .flatMap(user -> {
                    List<GrantedAuthority> rols = user.getRols()
                            .stream()
                            .map(String::toString)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    return Mono.just(new UserDetailsImp(user, user.getPassword(), rols));
                });
    }
}
