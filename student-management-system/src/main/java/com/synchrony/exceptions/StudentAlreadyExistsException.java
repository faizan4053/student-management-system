package com.synchrony.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentAlreadyExistsException extends RuntimeException{

    private String message;

    public StudentAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }

}
