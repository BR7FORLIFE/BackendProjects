package com.auth.auth.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequestDto(
    @NotBlank String username, 
    @NotBlank @Email String email, 
    @NotBlank String password) {

}
