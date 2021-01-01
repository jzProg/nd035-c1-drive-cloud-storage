package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
