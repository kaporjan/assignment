package com.an.assignment.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {

    }
    public NotFoundException(Exception e) {
        super(e);
    }

    public NotFoundException(String m) {
        super(m);
    }
}
