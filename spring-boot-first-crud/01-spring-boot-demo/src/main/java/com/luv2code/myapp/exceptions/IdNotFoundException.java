package com.luv2code.myapp.exceptions;


public class IdNotFoundException extends RuntimeException {
    
        public IdNotFoundException (Integer id) {
            super("ID not found: " + id);
        }
}
