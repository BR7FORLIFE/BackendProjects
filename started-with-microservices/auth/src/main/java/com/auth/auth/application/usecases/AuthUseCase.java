package com.auth.auth.application.usecases;

import com.auth.auth.application.command.actions.LoginUserCommand;
import com.auth.auth.application.command.actions.RegisterUserCommand;
import com.auth.auth.application.command.response.LoginUserCommandResult;
import com.auth.auth.application.command.response.RegisterUserCommandResult;

import reactor.core.publisher.Mono;

public interface AuthUseCase {
    Mono<RegisterUserCommandResult> registerUser(RegisterUserCommand cmd);

    Mono<LoginUserCommandResult> loginUser(LoginUserCommand cmd);
}
