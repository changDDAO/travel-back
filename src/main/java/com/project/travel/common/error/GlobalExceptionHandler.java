package com.project.travel.common.error;

import com.project.travel.common.error.exceptions.CustomRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomRuntimeException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return createResponseEntity(response);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(ErrorResponse response) {
        return ResponseEntity.status(response.httpStatus()).body(response);
    }
}
