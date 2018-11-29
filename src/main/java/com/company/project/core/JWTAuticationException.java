package com.company.project.core;

import org.springframework.security.core.AuthenticationException;

public class JWTAuticationException extends AuthenticationException {

    public JWTAuticationException(String message) {
        super(message);
    }

    public JWTAuticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
