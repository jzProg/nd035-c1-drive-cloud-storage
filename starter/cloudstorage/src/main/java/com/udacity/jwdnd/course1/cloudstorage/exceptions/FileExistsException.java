package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class FileExistsException extends RuntimeException {
    public FileExistsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
