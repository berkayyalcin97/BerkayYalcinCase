package com.example.berkayyalcincase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EarthquakeApiRequestException extends Exception{

    public EarthquakeApiRequestException(String message) {
        super(message);
    }
}