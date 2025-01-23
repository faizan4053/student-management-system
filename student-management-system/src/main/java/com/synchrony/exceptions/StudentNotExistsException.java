package com.synchrony.exceptions;

public class StudentNotExistsException extends RuntimeException{

    private String message;

    public StudentNotExistsException() {}

    public StudentNotExistsException(String message){
        super(message);
        this.message=message;
    }

}
