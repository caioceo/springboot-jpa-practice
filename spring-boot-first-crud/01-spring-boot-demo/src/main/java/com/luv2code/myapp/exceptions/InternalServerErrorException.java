package com.luv2code.myapp.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(){
        super("Internal Server Error");
    }
}
