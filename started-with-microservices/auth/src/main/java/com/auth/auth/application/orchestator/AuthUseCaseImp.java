package com.auth.auth.application.orchestator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.auth.application.command.actions.LoginUserCommand;
import com.auth.auth.application.command.actions.RegisterUserCommand;
import com.auth.auth.application.command.response.LoginUserCommandResult;
import com.auth.auth.application.command.response.RegisterUserCommandResult;
import com.auth.auth.application.exceptions.UserAlreadyExistsException;
import com.auth.auth.application.ports.IAuthRepositoryPort;
import com.auth.auth.application.ports.JwtServicePort;
import com.auth.auth.application.usecases.AuthUseCase;
import com.auth.auth.domain.model.UserModel;
import com.auth.auth.infra.security.manager.UserDetailsImp;

import reactor.core.publisher.Mono;

@Service
public class AuthUseCaseImp implements AuthUseCase {

    private final PasswordEncoder passwordEncoder;
    private final IAuthRepositoryPort authRepositoryPort;
    private final JwtServicePort jwtServicePort;

    public AuthUseCaseImp(IAuthRepositoryPort authRepositoryPort, PasswordEncoder passwordEncoder,
            JwtServicePort jwtServicePort) {
        this.authRepositoryPort = authRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtServicePort = jwtServicePort;
    }

    @Override
    public Mono<LoginUserCommandResult> loginUser(LoginUserCommand cmd) {
        
        return null;
    }

    @Override
    public Mono<RegisterUserCommandResult> registerUser(RegisterUserCommand cmd) {
        return authRepositoryPort.findByEmail(cmd.email())
                .flatMap(user -> Mono.<RegisterUserCommandResult>error(
                        new UserAlreadyExistsException()))
                .switchIfEmpty(
                        Mono.defer(() -> {

                            UserModel newUser = UserModel.createDraft(
                                    cmd.email(),
                                    cmd.username(),
                                    passwordEncoder.encode(cmd.password()));

                            return authRepositoryPort.save(newUser)

                                    .flatMap(savedUser -> {

                                        List<GrantedAuthority> authorities = savedUser.getRols()
                                                .stream()
                                                .map(String::toString)
                                                .map(SimpleGrantedAuthority::new)
                                                .collect(Collectors.toList());

                                        UserDetailsImp details = new UserDetailsImp(
                                                savedUser,
                                                savedUser.getPassword(),
                                                authorities);

                                        return jwtServicePort.generateAccessToken(details)

                                                .map(accessToken -> new RegisterUserCommandResult(
                                                        accessToken,
                                                        "user register succesfull!"));
                                    });
                        }));
    }
}
