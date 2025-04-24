package com.project.travel.common.error.exceptions;

import com.project.travel.common.error.BaseErrorCode;

public class ConflictException extends CustomRuntimeException {

    public ConflictException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
