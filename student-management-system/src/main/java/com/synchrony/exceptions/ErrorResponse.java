package com.synchrony.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {
    private int statusCode;
    private String message;

    public  ErrorResponse(String message){
        super();
        this.message=message;
    }
}
