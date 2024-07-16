package com.rmtech.next_trip_planner.trip;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @autor raqueldarellimichelon
 * created on 16/07/24 inside the package - com.rmtech.next_trip_planner.trip
 **/
@ControllerAdvice
public class CustomTripExceptionHandler {

    @ExceptionHandler(InvalidEndDateException.class)
    public ResponseEntity<String> handleInvalidEndDateException(InvalidEndDateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}


