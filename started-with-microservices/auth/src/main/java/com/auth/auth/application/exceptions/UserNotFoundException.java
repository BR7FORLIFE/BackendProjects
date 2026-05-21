package com.auth.auth.application.exceptions;

import com.auth.auth.application.ZGlobalApplicationExeption;

public class UserNotFoundException extends ZGlobalApplicationExeption {

    public UserNotFoundException() {
        super("user not found!");
    }
}
