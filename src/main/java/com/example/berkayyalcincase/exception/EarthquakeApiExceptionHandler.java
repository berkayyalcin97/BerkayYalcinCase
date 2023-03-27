package com.example.berkayyalcincase.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EarthquakeApiExceptionHandler {
    @ExceptionHandler(value = {EarthquakeApiRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(EarthquakeApiRequestException e){
        EarthquakeApiException apiException =new EarthquakeApiException(e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
