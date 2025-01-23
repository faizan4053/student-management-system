package com.synchrony.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class SuccessResponse {
    private String message;

    public SuccessResponse(String message){
        super();
        this.message=message;
    }
}
