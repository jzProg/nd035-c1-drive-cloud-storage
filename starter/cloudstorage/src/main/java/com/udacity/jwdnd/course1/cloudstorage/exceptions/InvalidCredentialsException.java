package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
