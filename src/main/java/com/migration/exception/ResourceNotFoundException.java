package com.migration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
    //public static final long serial
    public ResourceNotFoundException(String message){
        super(message);
    }
}
