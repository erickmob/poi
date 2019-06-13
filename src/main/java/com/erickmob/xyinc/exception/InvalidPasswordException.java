package com.erickmob.xyinc.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException() {
        super("Incorrect password");
    }

}