package com.POS.system.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserException extends Throwable {
    public UserException(String message) {
        super(message);
    }
}
