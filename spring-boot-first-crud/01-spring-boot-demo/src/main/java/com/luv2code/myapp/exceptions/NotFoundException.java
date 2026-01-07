package com.luv2code.myapp.exceptions;


public class NotFoundException extends RuntimeException {
    
        public NotFoundException (String field, Integer id) {
            super(field + " not found: " + id);
        }
}
