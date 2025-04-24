package com.project.travel.common.error;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus httpStatus,

        String errorMessage
) {

    public ErrorResponse(BaseErrorCode errorCode) {
        this(errorCode.getStatus(), errorCode.getMessage());
    }
}
