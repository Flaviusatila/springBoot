package com.iftm.course.resources.exceptions;

import com.iftm.course.service.exceptions.DataBaseException;
import com.iftm.course.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandarError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resouce not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandarError err = new StandarError( Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status( status ).body( err );
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandarError> database(DataBaseException e , HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandarError err = new StandarError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status( status ).body( err );
    }

}
