package com.auth.auth.infra.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.auth.application.command.actions.LoginUserCommand;
import com.auth.auth.application.command.actions.RegisterUserCommand;
import com.auth.auth.application.dto.request.LoginUserRequestDto;
import com.auth.auth.application.dto.request.RegisterUserRequestDto;
import com.auth.auth.application.dto.response.LoginUserResponseDto;
import com.auth.auth.application.dto.response.RegisterUserResponseDto;
import com.auth.auth.application.usecases.AuthUseCase;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<RegisterUserResponseDto>> register(@Valid @RequestBody RegisterUserRequestDto dto) {
        RegisterUserCommand cmd = new RegisterUserCommand(dto.username(), dto.email(), dto.password());

        return authUseCase.registerUser(cmd)
                .flatMap(res -> Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body(new RegisterUserResponseDto(res.jwt(), res.message()))));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginUserResponseDto>> login(@Valid @RequestBody LoginUserRequestDto dto) {
        LoginUserCommand cmd = new LoginUserCommand(dto.email(), dto.password());

        return authUseCase.loginUser(cmd)
                .flatMap(
                        res -> Mono.just(ResponseEntity.ok().body(new LoginUserResponseDto(res.jwt(), res.message()))));
    }
}
