package com.auth.auth.infra.security.manager;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.auth.auth.domain.model.UserModel;
import com.auth.auth.shared.jwt.JwtService;

import reactor.core.publisher.Mono;

public class ReactiveAuthenticationManagerImp implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    public ReactiveAuthenticationManagerImp(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String tokenJwt = (String) authentication.getCredentials();

        return jwtService.validateAccessToken(tokenJwt)
                .flatMap(claims -> {
                    UUID userId = null;
                    String username = claims.getSubject();
                    List<String> rols;

                    try {
                        userId = UUID.fromString(claims.getClaimAsString("userId"));
                    } catch (Exception e) {
                        throw new BadCredentialsException("error to obtain userId!");
                    }

                    try {
                        rols = Optional.ofNullable(claims.getStringListClaim("ROLS"))
                                .orElse(List.of());
                    } catch (Exception e) {
                        throw new RuntimeException("error to obtain the rols!");
                    }

                    List<GrantedAuthority> authorities = rols.stream()
                            .map(String::toString)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UserModel user = UserModel.createDetails(userId, username);

                    UserDetailsImp details = new UserDetailsImp(user, null, authorities);

                    Authentication auth = new UsernamePasswordAuthenticationToken(details, tokenJwt, authorities);

                    return Mono.just(auth);
                });
    }
}
