package com.luv2code.myapp.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String instance, String field, String value) {
        super( instance + " already exists with " + field + ": " + value );
    }
}
