package com.auth.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.auth.auth.infra.security.manager.ReactiveAuthenticationManagerImp;
import com.auth.auth.shared.jwt.JwtService;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final String[] PUBLIC_PATHS = { "/auth/**" };

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity,
            AuthenticationWebFilter webFilter) throws Exception {
        return serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        exchange -> exchange.pathMatchers(PUBLIC_PATHS).permitAll().anyExchange().authenticated())
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .addFilterAt(webFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public AuthenticationWebFilter authenticationWebFilter(
            ReactiveAuthenticationManager authenticationManager,
            ServerAuthenticationConverter bearerAuthenticationConverter) {

        AuthenticationWebFilter filter = new AuthenticationWebFilter(authenticationManager);
        filter.setServerAuthenticationConverter(bearerAuthenticationConverter);
        filter.setSecurityContextRepository(NoOpServerSecurityContextRepository.getInstance());

        return filter;
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new ReactiveAuthenticationManagerImp(this.jwtService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
