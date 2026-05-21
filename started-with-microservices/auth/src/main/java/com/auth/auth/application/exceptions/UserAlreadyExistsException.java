package com.auth.auth.application.exceptions;

import com.auth.auth.application.ZGlobalApplicationExeption;

public class UserAlreadyExistsException extends ZGlobalApplicationExeption {

    public UserAlreadyExistsException() {
        super("user already exists!");
    }
}
